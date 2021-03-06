package com.nistruct.sportstat.repository

import com.nistruct.sportstat.api.RetrofitInstance
import com.nistruct.sportstat.data.mappers.DataMapper
import com.nistruct.sportstat.data.mappers.PlayerResponseToPlayerMapper
import com.nistruct.sportstat.data.mappers.UserResponseToPlayerMapper
import com.nistruct.sportstat.data.models.api_models.PostPlayerRequestBody
import com.nistruct.sportstat.data.models.api_models.UserResponse
import com.nistruct.sportstat.data.models.ui_models.Player
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class PlayerRepositoryImpl
@Inject
constructor(
    private val userResponseToPlayerMapper: UserResponseToPlayerMapper
) : PlayerRepository {
    override suspend fun getPlayers() = flow {
        emit(RetrofitInstance.playerApi.getPlayers())
    }
        .map { playerResponseList ->
            playerResponseList.filter { !it.isTrainer }
                .map { playerResponse ->
                    userResponseToPlayerMapper.map(playerResponse)
                }
        }


    override suspend fun postPlayer(
        playerName: String,
        playerEmail: String,
        playerPhoneNumber: String,
        playerPosition: String,
        playerImage: String
    ) = flow {
        val postPlayerRequest = PostPlayerRequestBody(
            null, playerName,
            playerEmail, playerPhoneNumber, playerPosition, playerImage
        )

        emit(RetrofitInstance.playerApi.postPlayer(postPlayerRequest))
    }
        .map { userResponse ->
            userResponseToPlayerMapper.map(userResponse)
        }
}