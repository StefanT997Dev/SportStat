package com.nistruct.sportstat.data.models.api_models

import java.sql.Blob
import java.util.*

data class PlayerResponse(
    val id:Int,
    val name:String,
    val photo:String,
    val position:String,
    val email:String,
    val phoneNumber:String,
    val team:String,
    val sport:String,
    val isTrainer:Boolean
)