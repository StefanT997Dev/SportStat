package com.nistruct.sportstat.data.models.api_models

import java.util.*

data class Position(
        val id:UUID,
        val name:String,
        val sport: Sport
)