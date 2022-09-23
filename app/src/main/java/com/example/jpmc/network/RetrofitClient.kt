package com.example.jpmc.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    // base url
    private const val BASE_URL = "https://data.cityofnewyork.us/"
    private const val TIME_OUT = 60L

    // OkHttp will automatically log incoming and outgoing HTTP requests and responses to Logcat.
    // This is added for debugging purposes while development.
    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }


}