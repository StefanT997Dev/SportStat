package com.nistruct.sportstat.data.models.api_models

import java.sql.Blob
import java.util.*

data class Team(
        val id: UUID,
        val logo:Blob,
        val sport: Sport,
        val numberOfActivePlayers:Int,
        val numberOfPlayers:Int,
        val colors:String
)