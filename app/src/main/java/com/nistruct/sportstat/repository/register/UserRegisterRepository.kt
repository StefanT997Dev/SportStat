package com.nistruct.sportstat.repository.register

import com.nistruct.sportstat.data.models.ui_models.Trainer
import kotlinx.coroutines.flow.Flow

interface UserRegisterRepository{
    fun registerUser(
        username:String,
        email:String,
        password:String
    ): Flow<Trainer>
}
