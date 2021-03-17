package com.nistruct.sportstat.api

import com.nistruct.sportstat.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val playerApi:PlayerApi by lazy{
        retrofit.create(PlayerApi::class.java)
    }
}