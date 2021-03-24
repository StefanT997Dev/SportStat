package com.nistruct.sportstat.repository

import com.nistruct.sportstat.data.models.api_models.PostPlayerRequestBody
import com.nistruct.sportstat.data.models.api_models.UserResponse
import com.nistruct.sportstat.data.models.ui_models.Player
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    suspend fun getPlayers(): Flow<List<Player>>

    suspend fun postPlayer(
                           playerName:String,
                           playerEmail:String,
                           playerPhoneNumber:String,
                           playerPosition:String,
                           playerImage:String
    ): Flow<Player>
}