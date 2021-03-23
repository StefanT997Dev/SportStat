package com.nistruct.sportstat.data.mappers

import com.nistruct.sportstat.data.models.api_models.PostPlayerRequestBody
import com.nistruct.sportstat.data.models.ui_models.Player

class PostPlayerRequestBodyToPlayerMapper:DataMapper<PostPlayerRequestBody,Player>{
    override fun map(player: PostPlayerRequestBody): Player {
        return Player(name = player.playerName,image = player.playerImage,position = player.playerPosition, id = player.id)
    }
}