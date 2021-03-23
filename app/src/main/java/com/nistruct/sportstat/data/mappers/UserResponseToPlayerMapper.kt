package com.nistruct.sportstat.data.mappers

import com.nistruct.sportstat.data.models.api_models.PostPlayerRequestBody
import com.nistruct.sportstat.data.models.api_models.UserResponse
import com.nistruct.sportstat.data.models.ui_models.Player

class UserResponseToPlayerMapper:DataMapper<UserResponse,Player>{
    override fun map(player: UserResponse): Player {
        return Player(name = player.name,image = player.photo,position = player.position, id = player.id)
    }
}