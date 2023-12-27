package com.example.foodbrowser.data.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//for more complex project dependencies could be provided here from outside, but
//I broke dependency inversion rule here for simplifying
private const val BASE_URL = "https://uih0b7slze.execute-api.us-east-1.amazonaws.com/"

class ServicesInitializer {
    private val loggingInterceptor
        get() = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    private val client get() = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    private val callAdapter get() = RxJava2CallAdapterFactory.create()

    private val gson get() = GsonBuilder().create()

    private val converterFactory get() = GsonConverterFactory.create(gson)

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converterFactory)
            .build()
    }

    fun <T> init(serviceClass: Class<T>): T = retrofit.create(serviceClass)

}