package com.nistruct.sportstat.repository.login

import com.nistruct.sportstat.data.models.api_models.LoginUserRequestBody
import com.nistruct.sportstat.data.models.api_models.UserResponse
import com.nistruct.sportstat.data.models.ui_models.Trainer
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun loginUser(
        trainerEmail:String,
        trainerPassword:String
    ): Flow<Trainer>
}