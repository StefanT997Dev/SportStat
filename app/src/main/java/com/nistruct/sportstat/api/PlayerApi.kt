package com.nistruct.sportstat.api

import com.nistruct.sportstat.data.models.User
import retrofit2.http.GET

interface PlayerApi {
    @GET("v3/1f59fca4-8a90-410e-8e00-8628decdf725")
    suspend fun getPlayers():List<User>
}