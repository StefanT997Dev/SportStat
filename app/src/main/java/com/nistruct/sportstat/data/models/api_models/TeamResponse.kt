package com.nistruct.sportstat.data.models.api_models

import java.sql.Blob
import java.util.*

data class TeamResponse(
        val name:String,
        val id: UUID,
        val logo:String,
        val sport: Sport,
        val numberOfActivePlayers:Int,
        val numberOfPlayers:Int,
        val colors:String
)