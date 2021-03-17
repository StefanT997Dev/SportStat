package com.nistruct.sportstat.data.models

import java.sql.Blob
import java.util.*

data class Team(
      var id: UUID,
      var logo:Blob,
      var sport:Sport,
      var numberOfActivePlayers:Int,
      var numberOfPlayers:Int,
      var colors:String
)