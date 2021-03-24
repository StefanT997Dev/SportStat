package com.nistruct.sportstat.data.models.api_models

data class LoginUserRequestBody(
    val email: String,
    val password: String
)