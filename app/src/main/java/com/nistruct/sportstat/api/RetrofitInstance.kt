package com.nistruct.sportstat.api

import com.nistruct.sportstat.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
    }
}