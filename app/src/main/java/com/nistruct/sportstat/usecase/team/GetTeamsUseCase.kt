package com.nistruct.sportstat.usecase.team

import com.nistruct.sportstat.data.models.ui_models.Player
import com.nistruct.sportstat.data.models.ui_models.Team
import com.nistruct.sportstat.repository.PlayerRepository
import com.nistruct.sportstat.repository.team.TeamRepository
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetTeamsUseCase {
    suspend fun execute(): Flow<DataResult<List<Team>>>
}

class GetTeamsUseCaseImpl
@Inject
constructor(
    private val teamRepository: TeamRepository,
    private val coroutineDispatcher: CoroutineDispatcher
):GetTeamsUseCase{
    // also offloading data off main thread in case the caller forgot to launch it on a different dispatcher
    override suspend fun execute() = withContext(coroutineDispatcher) {
        teamRepository
            .getTeams()
            .map { teams -> DataResult.Success(teams) }
    }
}