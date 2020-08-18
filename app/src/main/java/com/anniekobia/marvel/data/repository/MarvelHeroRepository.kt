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
import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter


class MarvelHeroRepository {

    private val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
    lateinit var API_KEY: String
    lateinit var TIMESTAMP: String
    lateinit var HASH: String

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

//    fun generateMD5Hash(){
//        //get timestamp string
//        val tsLong = System.currentTimeMillis() / 1000
//        TIMESTAMP = tsLong.toString()
//
//
//
//        val hash = "35454B055CC325EA1AF2126E27707052"
//        val password = "ILoveJava"
//
//        val md: MessageDigest = MessageDigest.getInstance("MD5")
//        md.update(password.toByteArray())
//        val digest: ByteArray = md.digest()
//        val myHash: String = DatatypeConverter
//            .printHexBinary(digest).toUpperCase()
//
//        assertThat(myHash == hash).isTrue()
//    }



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