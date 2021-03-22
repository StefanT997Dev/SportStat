package com.nistruct.sportstat.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi{

    @POST("auth/login")
    fun login(
        @Field("username")username:String,
        @Field("password")password:String
    ):Any
}