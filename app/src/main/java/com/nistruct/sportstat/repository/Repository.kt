package com.nistruct.sportstat.repository

import com.nistruct.sportstat.api.RetrofitInstance
import com.nistruct.sportstat.data.models.User

class Repository {
    suspend fun getPlayers():List<User>{
        return RetrofitInstance.playerApi.getPlayers()
    }
}