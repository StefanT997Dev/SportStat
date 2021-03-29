package com.nistruct.sportstat.usecase.team

import com.nistruct.sportstat.data.models.ui_models.Team
import com.nistruct.sportstat.repository.PlayerRepository
import com.nistruct.sportstat.repository.team.TeamRepository
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PostTeamUseCase {
    suspend fun execute(request: PostTeamUseCaseRequest): Flow<DataResult.Success<Team>>
}

data class PostTeamUseCaseRequest(
    val logo:String,
    val name:String
)

class PostTeamUseCaseImpl
@Inject
constructor(
    private val teamRepository: TeamRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : PostTeamUseCase {
    override suspend fun execute(request: PostTeamUseCaseRequest) =
        withContext(coroutineDispatcher) {
            teamRepository
                .postTeam(
                    request.logo,
                    request.name
                )
                .map { team -> DataResult.Success(team) }
        }
}