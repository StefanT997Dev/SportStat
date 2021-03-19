package com.nistruct.sportstat.data.models.api_models

import java.util.*

data class Sport(
        val id:UUID,
        val name:String,
        val lengthOfPeriod:Int,
        val numberOfPeriods:Int
)