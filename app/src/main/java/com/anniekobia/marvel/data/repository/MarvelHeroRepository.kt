package com.anniekobia.marvel.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anniekobia.marvel.BuildConfig
import com.anniekobia.marvel.data.api.ApiService
import com.anniekobia.marvel.data.api.marvelapi.Marvelhero
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest


class MarvelHeroRepository {

    private val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
    private val API_KEY: String = BuildConfig.PUBLIC_KEY
    private val TIMESTAMP: String = "12345"
    lateinit var HASH: String

    private var marvelService: ApiService? = null
    private var marvelHeroLiveData: MutableLiveData<Marvelhero>? = null

    init {
        marvelHeroLiveData = MutableLiveData<Marvelhero>()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        marvelService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun generateMD5Hash(){
        //ts+privateKey+publicKey String combination
        val combination: String = TIMESTAMP+BuildConfig.PRIVATE_KEY+BuildConfig.PUBLIC_KEY
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

        HASH = hexString.toString()
    }

    fun searchMarvelHero(
        orderBy: String?,
        limit: Int?) {
        generateMD5Hash()
        marvelService?.getMarvelheroes(orderBy, limit,TIMESTAMP,API_KEY,HASH)
            ?.enqueue(object : Callback<Marvelhero?> {
                override fun onResponse(
                    call: Call<Marvelhero?>?,
                    response: Response<Marvelhero?>) {
                    if (response.body() != null) {
                        marvelHeroLiveData!!.postValue(response.body())
                    }
                }

                override fun onFailure(
                    call: Call<Marvelhero?>?,
                    t: Throwable?) {
                    marvelHeroLiveData!!.postValue(null)
                }
            })
    }

    fun getMarvelHeroLiveData(): LiveData<Marvelhero>? {
        return marvelHeroLiveData
    }
}