package com.nistruct.sportstat.data.mappers

import com.nistruct.sportstat.data.models.api_models.TeamResponse
import com.nistruct.sportstat.data.models.ui_models.Team

class TeamResponseToTeamMapper:DataMapper<TeamResponse, Team> {
    override fun map(input: TeamResponse): Team {
        return Team(input.logo,input.name)
    }
}