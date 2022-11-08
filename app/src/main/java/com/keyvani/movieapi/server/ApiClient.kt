package com.keyvani.movieapi.server

import com.keyvani.movieapi.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private lateinit var retrofit: Retrofit
    private val client = OkHttpClient.Builder()
        .readTimeout(Constants.NETWORK_TIMEOUT,TimeUnit.SECONDS)
        .writeTimeout(Constants.NETWORK_TIMEOUT,TimeUnit.SECONDS)
        .connectTimeout(Constants.NETWORK_TIMEOUT,TimeUnit.SECONDS)
        .build()

    fun getClient():Retrofit{
        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }
}