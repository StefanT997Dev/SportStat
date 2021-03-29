package com.nistruct.sportstat.api

import com.nistruct.sportstat.data.models.api_models.TeamResponse
import com.nistruct.sportstat.data.models.api_models.team.PostTeamRequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TeamApi {
    @GET("api/teams")
    suspend fun getTeams(): List<TeamResponse>

    @POST("api/team")
    suspend fun postTeam(@Body teamRequestBody: PostTeamRequestBody): TeamResponse
}