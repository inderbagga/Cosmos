package com.inderbagga.cosmos.remote

import com.inderbagga.cosmos.remote.API.Companion.SOURCE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(SOURCE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service: API by lazy {
        retrofit.create(API::class.java)
    }
}