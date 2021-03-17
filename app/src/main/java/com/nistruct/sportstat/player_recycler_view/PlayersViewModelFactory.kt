package com.nistruct.sportstat.player_recycler_view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.repository.Repository

class PlayersViewModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayersViewModel(repository) as T
    }

}