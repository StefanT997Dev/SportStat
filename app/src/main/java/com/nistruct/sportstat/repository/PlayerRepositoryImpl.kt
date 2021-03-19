package com.nistruct.sportstat.repository

import com.nistruct.sportstat.api.RetrofitInstance
import com.nistruct.sportstat.data.mappers.DataMapper
import com.nistruct.sportstat.data.mappers.PlayerResponseToPlayerMapper
import com.nistruct.sportstat.data.models.api_models.PlayerResponse
import com.nistruct.sportstat.data.models.ui_models.Player
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

class PlayerRepositoryImpl : PlayerRepository {
    private val playerResponseToPlayerMapper: DataMapper<PlayerResponse, Player> =
        PlayerResponseToPlayerMapper()

    override suspend fun getPlayers() = flow {
        emit(RetrofitInstance.playerApi.getPlayers())
    }
        .map { playerResponseList ->
            playerResponseList.map { playerResponse ->
                playerResponseToPlayerMapper.map(playerResponse)
            }
        }

    override suspend fun postPlayer(player:PlayerResponse) = flow {
        emit(RetrofitInstance.playerApi.postPlayer(player))
    }
}