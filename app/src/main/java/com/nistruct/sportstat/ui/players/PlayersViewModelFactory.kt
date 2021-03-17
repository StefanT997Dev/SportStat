package com.nistruct.sportstat.ui.players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.repository.PlayerRepository

class PlayersViewModelFactory(private val repository: PlayerRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayersViewModel(repository) as T
    }

}