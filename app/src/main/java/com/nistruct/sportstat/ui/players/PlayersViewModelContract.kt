package com.nistruct.sportstat.ui.players

import androidx.lifecycle.LiveData
import com.nistruct.sportstat.data.models.api_models.PlayerResponse
import com.nistruct.sportstat.data.models.ui_models.Player
import com.nistruct.sportstat.usecase.result.DataResult

interface PlayersViewModelContract {
    val playersLiveData: LiveData<DataResult<List<Player>>>

    fun getPlayers()
}