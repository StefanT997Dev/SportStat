package com.nistruct.sportstat.ui.players

import androidx.lifecycle.LiveData
import com.nistruct.sportstat.data.models.api_models.Player

interface PlayersViewModelContract {
    val playersLiveData: LiveData<List<Player>>

    fun getPlayers()
}