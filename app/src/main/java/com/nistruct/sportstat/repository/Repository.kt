package com.nistruct.sportstat.repository

import com.nistruct.sportstat.api.RetrofitInstance
import com.nistruct.sportstat.data.models.api_models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository {
    suspend fun getPlayers():Flow<List<User>> {
        val flow=flow<List<User>> {
            emit(RetrofitInstance.playerApi.getPlayers())
        }

        return flow
    }
}