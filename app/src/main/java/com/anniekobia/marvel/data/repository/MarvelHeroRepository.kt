package com.anniekobia.marvel.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anniekobia.marvel.BuildConfig
import com.anniekobia.marvel.data.api.MarvelApiService
import com.anniekobia.marvel.data.api.SuperheroApiService
import com.anniekobia.marvel.data.api.model.MarvelSuperhero
import com.anniekobia.marvel.data.api.model.marvelapi.Marvelhero
import com.anniekobia.marvel.data.api.model.marvelapi.Result
import com.anniekobia.marvel.data.api.model.superheroapi.Superhero
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest


class MarvelHeroRepository {

    //MarvelAPI
    private val BASE_URL_MARVELAPI = "https://gateway.marvel.com:443/v1/public/"
    private val API_KEY_MARVELAPI: String = BuildConfig.PUBLIC_KEY
    private val TIMESTAMP_MARVELAPI: String = "12345"
    lateinit var HASH_MARVELAPI: String
    private var marvelServiceMarvel: MarvelApiService? = null
//    private var marvelHeroLiveData: MutableLiveData<Marvelhero>? = null

    //SuperheroAPI
    private val BASE_URL_SUPERHEROAPI = "https://superheroapi.com/api/"
    private val ACCESS_TOKEN_SUPERHEROAPI: Long = BuildConfig.ACCESS_TOKEN
    private var superheroServiceMarvel: SuperheroApiService? = null

    //Both APIs combined
    private var marvelSuperheroList: ArrayList<MarvelSuperhero>? = null
    private var marvelSuperheroLiveData: MutableLiveData<ArrayList<MarvelSuperhero>>? = null


    init {
//        marvelHeroLiveData = MutableLiveData<Marvelhero>()
        marvelSuperheroLiveData = MutableLiveData<ArrayList<MarvelSuperhero>>()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        //MarvelAPI
        marvelServiceMarvel = Retrofit.Builder()
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
        orderBy: String?,
        limit: Int?
    ) {
        generateMD5Hash()
        marvelServiceMarvel?.getMarvelheroes(
            orderBy,
            limit,
            TIMESTAMP_MARVELAPI,
            API_KEY_MARVELAPI,
            HASH_MARVELAPI
        )
            ?.enqueue(object : Callback<Marvelhero?> {
                override fun onResponse(
                    call: Call<Marvelhero?>?,
                    response: Response<Marvelhero?>
                ) {
                    if (response.body() != null) {
                        val results = (response.body())?.data?.results
                        getSuperHeroDetails(results)
                        Log.e("Finished","Wait for Posts?")
                        marvelSuperheroLiveData?.postValue(marvelSuperheroList)
//                        marvelHeroLiveData!!.postValue(response.body())
                    }
                }

                override fun onFailure(
                    call: Call<Marvelhero?>?,
                    t: Throwable?
                ) {
                    Log.e("Finished","Failed?")
                    marvelSuperheroLiveData?.postValue(null)
//                    marvelHeroLiveData!!.postValue(null)
                }
            })
    }

    fun getSuperHeroDetails(results: List<Result>?) {
        if (results != null) {
            var nameList: ArrayList<String>? = null
            for (result in results) {
                var characterName = result.name
                characterName = characterName.substringBefore("(")
                if (nameList != null) {
                    if (!nameList.contains(characterName)) {
                        searchSuperHero(result, ACCESS_TOKEN_SUPERHEROAPI, characterName)
                        nameList?.add(characterName)
                    }
                } else {
                    searchSuperHero(result, ACCESS_TOKEN_SUPERHEROAPI, characterName)
                    nameList?.add(characterName)
                }
            }
        }
    }

    fun searchSuperHero(
        marvelApiResult: Result,
        access_token: Long,
        name: String
    ) {
        superheroServiceMarvel?.getSuperheroDetails(access_token, name)
            ?.enqueue(object : Callback<Superhero?> {
                override fun onResponse(
                    call: Call<Superhero?>?,
                    response: Response<Superhero?>
                ) {
                    if (response.body() != null) {
                        val superheroApiResult = response.body()
                        if (superheroApiResult != null) {
                            if (superheroApiResult.response == "error") {
                                Log.e(
                                    "SUPERHEROAPIHERE",
                                    name + ": " + "Something happened/not found error"
                                )
                            } else {
                                Log.e(
                                    "SUPERHEROAPIHERE",
                                    name + ": " + superheroApiResult.results[0].name + "," + superheroApiResult.results[0].biography.fullName
                                )
                                setMarvelSuperheroDetails(marvelApiResult, superheroApiResult)
                            }
                        }
                    }
                }

                override fun onFailure(
                    call: Call<Superhero?>?,
                    t: Throwable?
                ) {
                    Log.e("SUPERHEROAPIHERE", name + ": " + "Something happened")
                }
            })
    }

    fun setMarvelSuperheroDetails(
        marvelApiResult: Result,
        superheroApiResult: Superhero
    ) {
        val comicslist = arrayListOf<String>()
        for (comic in marvelApiResult.comics.items) {
            comicslist.add(comic.name)
        }
        val superheroImage =
            marvelApiResult.thumbnail.path + "." + marvelApiResult.thumbnail.extension
        val marvelsuperhero = MarvelSuperhero(
            superheroApiResult.results[0].appearance.gender,
            superheroApiResult.results[0].appearance.race,
            superheroApiResult.results[0].biography.aliases,
            comicslist as List<String>,
            marvelApiResult.description,
            superheroApiResult.results[0].biography.fullName,
            marvelApiResult.name,
            superheroImage
        )
        marvelSuperheroList?.add(marvelsuperhero)
    }

    fun getMarvelHeroLiveData(): LiveData<ArrayList<MarvelSuperhero>>? {
        marvelSuperheroLiveData?.postValue(marvelSuperheroList)
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
}