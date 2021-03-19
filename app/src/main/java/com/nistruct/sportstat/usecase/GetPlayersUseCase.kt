package com.nistruct.sportstat.usecase

import com.nistruct.sportstat.data.models.ui_models.Player
import com.nistruct.sportstat.repository.PlayerRepository
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface GetPlayersUseCase {
    suspend fun execute(): Flow<DataResult<List<Player>>>
}

class GetPlayersUseCaseImpl (
    private val playerRepository: PlayerRepository,
    private val coroutineDispatcher: CoroutineDispatcher
):GetPlayersUseCase{
    // also offloading data off main thread in case the caller forgot to launch it on a different dispatcher
    override suspend fun execute() = withContext(coroutineDispatcher) {
        playerRepository
            .getPlayers()
            .map { players -> DataResult.Success(players) }
    }
}