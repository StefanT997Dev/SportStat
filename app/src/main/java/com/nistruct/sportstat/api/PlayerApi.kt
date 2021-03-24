package com.nistruct.sportstat.api

import com.nistruct.sportstat.data.models.api_models.LoginUserRequestBody
import com.nistruct.sportstat.data.models.api_models.PostPlayerRequestBody
import com.nistruct.sportstat.data.models.api_models.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PlayerApi {
    @GET("v3/425cc7a2-a129-4e48-98a1-c90d41a16632")
    suspend fun getPlayers(): List<UserResponse>

    @POST("players")
    suspend fun postPlayer(@Body playerRequestBody: PostPlayerRequestBody):UserResponse
}