package com.anniekobia.marvel.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anniekobia.marvel.data.api.ApiService
import com.anniekobia.marvel.data.api.marvelapi.Marvelhero
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MarvelHeroRepository {

    private val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
    private  val API_KEY = "98045fa11906a1afd4de0216cbfc033f"
    private  val TIMESTAMP = "12345"
    private  val HASH = "271B87F6539D8FAD5DEC94EDBF954741"

    private var marvelService: ApiService? = null
    private var marvelHeroLiveData: MutableLiveData<Marvelhero>? = null

    init {
        marvelHeroLiveData = MutableLiveData<Marvelhero>()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        interceptor.level(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        marvelService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun getMD5Hash(){
        val tsLong = System.currentTimeMillis() / 1000
        val ts = tsLong.toString()
    }

    fun searchMarvelHero(
        orderBy: String?,
        limit: Int?) {
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