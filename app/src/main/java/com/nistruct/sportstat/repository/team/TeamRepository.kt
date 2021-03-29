package com.nistruct.sportstat.repository.team

import com.nistruct.sportstat.data.models.ui_models.Team
import kotlinx.coroutines.flow.Flow

interface TeamRepository {
    suspend fun postTeam(
        logo:String,
        name:String
    ): Flow<Team>
}