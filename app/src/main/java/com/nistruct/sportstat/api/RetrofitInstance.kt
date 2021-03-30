package com.nistruct.sportstat.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL="https://run.mocky.io/"

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val playerApi:PlayerApi by lazy{
        retrofit.create(PlayerApi::class.java)
    }

    val authApi:AuthApi by lazy{
        retrofit.create(AuthApi::class.java)
    }

    val teamApi:TeamApi by lazy{
        retrofit.create(TeamApi::class.java)
    }

    val statisticsApi:StatisticsApi by lazy{
        retrofit.create(StatisticsApi::class.java)
    }
}