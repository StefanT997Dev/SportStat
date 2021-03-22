package com.nistruct.sportstat.data.mappers

import com.nistruct.sportstat.data.models.api_models.UserResponse
import com.nistruct.sportstat.data.models.ui_models.Player

class PlayerResponseToPlayerMapper():DataMapper<UserResponse,Player> {
    override fun map(input: UserResponse): Player {
        return Player(input.id,input.photo,input.name,input.position)
    }
}