package com.nistruct.sportstat.api

import com.nistruct.sportstat.data.models.api_models.User
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface PlayerApi {
    @GET("v3/425cc7a2-a129-4e48-98a1-c90d41a16632")
    suspend fun getPlayers(): List<User>
}