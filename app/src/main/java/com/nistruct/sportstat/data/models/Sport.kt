package com.nistruct.sportstat.data.models

import java.util.*

data class Sport(
        var id:UUID,
        var name:String,
        var lengthOfPeriod:Int,
        var numberOfPeriods:Int
)