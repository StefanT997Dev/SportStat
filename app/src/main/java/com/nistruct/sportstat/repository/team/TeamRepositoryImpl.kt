package com.nistruct.sportstat.repository.team

import com.nistruct.sportstat.api.RetrofitInstance
import com.nistruct.sportstat.data.mappers.TeamResponseToTeamMapper
import com.nistruct.sportstat.data.models.api_models.team.PostTeamRequestBody
import com.nistruct.sportstat.data.models.ui_models.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TeamRepositoryImpl
@Inject
constructor(
    private val teamResponseToTeamMapper: TeamResponseToTeamMapper
) :TeamRepository {
    override suspend fun getTeams()=flow {
        emit(RetrofitInstance.teamApi.getTeams())
    }
        .map { teamResponseList ->
            teamResponseList.filter { teamResponseList.isNotEmpty() }
                .map { teamResponse ->
                    teamResponseToTeamMapper.map(teamResponse)
                }
        }

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