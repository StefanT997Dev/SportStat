package com.nistruct.sportstat.data.mappers

import com.nistruct.sportstat.data.models.api_models.UserResponse
import com.nistruct.sportstat.data.models.ui_models.Trainer

class UserResponseToTrainerMapper:DataMapper<UserResponse, Trainer> {
    override fun map(input: UserResponse): Trainer {
        return Trainer(input.id, input.name, input.photo, input.email, input.isTrainer)
    }
}