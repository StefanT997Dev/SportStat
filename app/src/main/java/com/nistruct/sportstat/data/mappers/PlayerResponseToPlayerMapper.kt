package com.nistruct.sportstat.data.mappers

import com.nistruct.sportstat.data.models.api_models.PlayerResponse
import com.nistruct.sportstat.data.models.ui_models.Player

class PlayerResponseToPlayerMapper():DataMapper<PlayerResponse,Player> {
    override fun map(input: PlayerResponse): Player {
        return Player(input.id,input.photo,input.name,input.position)
    }
}