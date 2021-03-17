package com.nistruct.sportstat.repository

import com.nistruct.sportstat.api.RetrofitInstance
import com.nistruct.sportstat.data.models.api_models.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayerRepositoryImpl:PlayerRepository {
    override suspend fun getPlayers():Flow<List<Player>> {
        val flow=flow<List<Player>> {
            emit(RetrofitInstance.playerApi.getPlayers())
        }

        return flow
    }
}