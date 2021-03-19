package com.nistruct.sportstat.usecase

import com.nistruct.sportstat.data.models.api_models.PlayerResponse
import com.nistruct.sportstat.data.models.ui_models.Player
import com.nistruct.sportstat.repository.PlayerRepository
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface PostPlayerUseCase {
    suspend fun execute(): Flow<DataResult.Success<PlayerResponse>>
}

class PostPlayerUseCaseImpl (
    private val playerRepository: PlayerRepository,
    private val coroutineDispatcher: CoroutineDispatcher,
    private val player: PlayerResponse
):PostPlayerUseCase{
    override suspend fun execute() = withContext(coroutineDispatcher) {
        playerRepository
            .postPlayer(player)
            .map { player -> DataResult.Success(player) }
    }
}