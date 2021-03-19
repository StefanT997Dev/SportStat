package com.nistruct.sportstat.repository

import com.nistruct.sportstat.data.models.api_models.PlayerResponse
import com.nistruct.sportstat.data.models.ui_models.Player
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PlayerRepository {
    suspend fun getPlayers(): Flow<List<Player>>

    suspend fun postPlayer(player:PlayerResponse): Flow<PlayerResponse>
}