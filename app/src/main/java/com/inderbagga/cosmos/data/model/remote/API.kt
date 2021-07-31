package com.inderbagga.cosmos.remote

import com.inderbagga.cosmos.data.model.Info
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    companion object{

        const val SOURCE="https://api.nasa.gov/"
        const val KEY="85d0N4Qg8nlATTpRCC3fPgdL7bWbZXGELk4AmFMV"
    }

    @GET("planetary/apod")
    suspend fun getRemoteInfo( @Query("api_key") apiKey:String): Info?
}