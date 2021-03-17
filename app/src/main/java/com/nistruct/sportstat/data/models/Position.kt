package com.nistruct.sportstat.data.models

import java.util.*

data class Position(
        var id:UUID,
        var name:String,
        var sport:Sport
)