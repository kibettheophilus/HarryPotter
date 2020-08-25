package com.anniekobia.marvel.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anniekobia.marvel.BuildConfig
import com.anniekobia.marvel.data.api.MarvelApiService
import com.anniekobia.marvel.data.api.SuperheroApiService
import com.anniekobia.marvel.data.api.model.MarvelSuperhero
import com.anniekobia.marvel.data.api.model.marvelapi.Result
import com.anniekobia.marvel.data.api.model.superheroapi.Superhero
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest


class MarvelHeroRepository {

    //MarvelAPI
    private val BASE_URL_MARVELAPI = "https://gateway.marvel.com:443/v1/public/"
    private val API_KEY_MARVELAPI: String = BuildConfig.PUBLIC_KEY
    private val TIMESTAMP_MARVELAPI: String = "12345"
    lateinit var HASH_MARVELAPI: String
    private var marvelApiService: MarvelApiService

    //SuperheroAPI
    private val BASE_URL_SUPERHEROAPI = "https://superheroapi.com/api/"
    private val ACCESS_TOKEN_SUPERHEROAPI: Long = BuildConfig.ACCESS_TOKEN
    private var superheroServiceMarvel: SuperheroApiService

    //Both APIs combined
    private var marvelSuperheroList = ArrayList<MarvelSuperhero>()
    private var marvelSuperheroLiveData = MutableLiveData<ArrayList<MarvelSuperhero>>()


    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        //MarvelAPI
        marvelApiService = Retrofit.Builder()
                .baseUrl(BASE_URL_MARVELAPI)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MarvelApiService::class.java)

        //SuperheroAPI
        superheroServiceMarvel = Retrofit.Builder()
                .baseUrl(BASE_URL_SUPERHEROAPI)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SuperheroApiService::class.java)
    }


    fun searchMarvelHero(
            orderBy: String,
            limit: Int) {
        generateMD5Hash()

        CoroutineScope(Dispatchers.IO).launch {
            val response = marvelApiService.getMarvelheroes(
                    orderBy,
                    limit,
                    TIMESTAMP_MARVELAPI,
                    API_KEY_MARVELAPI,
                    HASH_MARVELAPI
            )
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val results = (response.body())!!.data.results
                        getSuperHeroDetails(results)
                    } else {
                        Log.e("Error: ", "${response.code()}")
                    }
                } catch (e: HttpException) {
                    Log.e("Exception: ", "${e.message}")
                    marvelSuperheroLiveData.postValue(null)
                } catch (e: Throwable) {
                    Log.e("Failed", "Overall Failure on Marvel API call")
                    marvelSuperheroLiveData.postValue(null)
                }
            }
        }
    }

    fun getSuperHeroDetails(results: List<Result>) {
        val nameList = arrayListOf<String>()
        for (result in results) {
            var characterName = result.name
            characterName = characterName.substringBefore("(")
            Log.e("Charactername:", characterName)
            if (!(nameList.contains(characterName))) {
                Log.e("Charactername2:", characterName)
                searchSuperhero(results, result, ACCESS_TOKEN_SUPERHEROAPI, characterName)
                nameList.add(characterName)
            }
        }
    }

    fun searchSuperhero(
            marvelApiResultsList: List<Result>,
            marvelApiResult: Result,
            accessToken: Long,
            name: String
    ) {

        CoroutineScope(Dispatchers.IO).launch {
            val response = superheroServiceMarvel.getSuperheroDetails(accessToken, name)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val superheroApiResult = response.body()!!
                        if (superheroApiResult.response == "error") {
                            if (marvelApiResult == marvelApiResultsList.last()) filterResults(marvelSuperheroList)

                        } else {
                            setMarvelSuperheroDetails(marvelApiResultsList, marvelApiResult, superheroApiResult)
                        }
                    } else {
                        Log.e("Error: ", "${response.code()}")
                        if (marvelApiResult == marvelApiResultsList.last()) filterResults(marvelSuperheroList)
                    }
                } catch (e: HttpException) {
                    Log.e("Exception: ", "${e.message}")
                    if (marvelApiResult == marvelApiResultsList.last()) filterResults(marvelSuperheroList)
                } catch (e: Throwable) {
                    Log.e("Failed: ", "${e.message}")
                    if (marvelApiResult == marvelApiResultsList.last()) filterResults(marvelSuperheroList)
                }
            }
        }
    }

    fun setMarvelSuperheroDetails(
            marvelApiResultsList: List<Result>,
            marvelApiResult: Result,
            superheroApiResult: Superhero
    ) {
        val comicslist = arrayListOf<String>()
        for (comic in marvelApiResult.comics.items) {
            comicslist.add(comic.name)
        }
        var superheroName = marvelApiResult.name
        superheroName = superheroName.substringBefore("(")
        val superheroImage =
                marvelApiResult.thumbnail.path + "." + marvelApiResult.thumbnail.extension

        val marvelsuperhero = MarvelSuperhero(
                superheroApiResult.results[0].appearance.gender,
                superheroApiResult.results[0].appearance.race,
                superheroApiResult.results[0].biography.aliases,
                comicslist as List<String>,
                marvelApiResult.description,
                superheroApiResult.results[0].biography.fullName,
                superheroName,
                superheroImage
        )
        marvelSuperheroList.add(marvelsuperhero)
        if (marvelApiResult == marvelApiResultsList.last()) {
            filterResults(marvelSuperheroList)
        }
    }

    fun postLiveData() {
        marvelSuperheroLiveData.postValue(marvelSuperheroList)
    }

    fun getMarvelHeroLiveData(): LiveData<ArrayList<MarvelSuperhero>> {
        return marvelSuperheroLiveData
    }

    fun generateMD5Hash() {
        //ts+privateKey+publicKey String combination
        val combination: String =
                TIMESTAMP_MARVELAPI + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY
        //Get HASH
        val digest = MessageDigest.getInstance("MD5")
        digest.update(combination.toByteArray())
        val messageDigest = digest.digest()
        val hexString = StringBuffer()
        for (i in messageDigest.indices) hexString.append(
                Integer.toHexString(
                        0xFF and messageDigest[i].toInt()
                )
        )
        HASH_MARVELAPI = hexString.toString()
    }

    private fun filterResults(marvelheros: ArrayList<MarvelSuperhero>) {
        val completeProfiles = arrayListOf<MarvelSuperhero>()
        for (hero in marvelheros) {
            val imageUnavailable = "image_not_available"
            if (imageUnavailable in hero.superheroImage) {
                Log.e("No image for: ", hero.superheroCharacterName)
            } else {
                completeProfiles.add(hero)
            }
        }
        //Organize SuperheroList in alphabetical order
        var completeProfileList = completeProfiles as List<MarvelSuperhero>
        completeProfileList = completeProfileList.sortedBy { it.superheroName }

        marvelSuperheroList = ArrayList(completeProfileList)
        postLiveData()
    }
}