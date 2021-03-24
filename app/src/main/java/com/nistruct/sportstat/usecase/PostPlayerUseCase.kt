package com.nistruct.sportstat.usecase

import com.nistruct.sportstat.data.models.ui_models.Player
import com.nistruct.sportstat.repository.PlayerRepository
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PostPlayerUseCase {
    suspend fun execute(request: PostPlayerUseCaseRequest): Flow<DataResult.Success<Player>>
}

data class PostPlayerUseCaseRequest(
    val playerName: String,
    val playerEmail: String,
    val playerPhoneNumber: String,
    val playerPosition: String,
    val playerImage: String
)

class PostPlayerUseCaseImpl
@Inject
constructor(
    private val playerRepository: PlayerRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : PostPlayerUseCase {
    override suspend fun execute(request: PostPlayerUseCaseRequest) =
        withContext(coroutineDispatcher) {
            playerRepository
                .postPlayer(
                    request.playerName,
                    request.playerEmail,
                    request.playerPhoneNumber,
                    request.playerPosition,
                    request.playerImage
                )
                .map { player -> DataResult.Success(player) }
        }
}