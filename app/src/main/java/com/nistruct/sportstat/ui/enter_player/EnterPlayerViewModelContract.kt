package com.nistruct.sportstat.ui.enter_player

import androidx.lifecycle.LiveData
import com.nistruct.sportstat.data.models.ui_models.Player
import com.nistruct.sportstat.usecase.result.DataResult

interface EnterPlayerViewModelContract {
    val playerLiveData: LiveData<DataResult<Player>>

    fun enterPlayer()
}