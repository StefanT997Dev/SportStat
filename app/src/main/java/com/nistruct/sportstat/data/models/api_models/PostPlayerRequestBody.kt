package com.nistruct.sportstat.data.models.api_models

data class PostPlayerRequestBody(
    val id: Int?,
    val playerName:String,
    val playerEmail:String,
    val playerPhoneNumber:String,
    val playerPosition:String,
    val playerImage:String
)