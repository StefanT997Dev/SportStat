package com.nistruct.sportstat.repository.team

import com.nistruct.sportstat.api.RetrofitInstance
import com.nistruct.sportstat.data.mappers.TeamResponseToTeamMapper
import com.nistruct.sportstat.data.models.api_models.team.PostTeamRequestBody
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TeamRepositoryImpl
@Inject
constructor(
    private val teamResponseToTeamMapper: TeamResponseToTeamMapper
)
    :TeamRepository {
    override suspend fun postTeam(logo: String, name: String) = flow {
        val postTeamRequest = PostTeamRequestBody(
            logo,name
        )

        emit(RetrofitInstance.teamApi.postTeam(postTeamRequest))
    }
        .map {  teamResponse->
            teamResponseToTeamMapper.map(teamResponse)
        }
}