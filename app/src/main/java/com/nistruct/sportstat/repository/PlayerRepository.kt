package com.nistruct.sportstat.repository

import com.nistruct.sportstat.data.models.api_models.Player
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    suspend fun getPlayers(): Flow<List<Player>>
}