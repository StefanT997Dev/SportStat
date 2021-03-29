package com.nistruct.sportstat.api

import com.nistruct.sportstat.data.models.api_models.LoginUserRequestBody
import com.nistruct.sportstat.data.models.api_models.RegisterUserRequestBody
import com.nistruct.sportstat.data.models.api_models.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi{
    @POST("api/register")
    suspend fun registerTrainer(@Body registerUserRequestBody:RegisterUserRequestBody):UserResponse

    @POST("api/login")
    suspend fun loginTrainer(@Body loginUserRequestBody: LoginUserRequestBody): UserResponse
}