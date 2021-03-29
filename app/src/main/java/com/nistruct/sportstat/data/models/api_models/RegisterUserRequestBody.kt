package com.nistruct.sportstat.data.models.api_models

data class RegisterUserRequestBody(
    val username: String,
    val email: String,
    val password: String
)