package com.nistruct.sportstat.ui.players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.repository.PlayerRepository
import com.nistruct.sportstat.usecase.GetPlayersUseCase

class PlayersViewModelFactory(private val useCase: GetPlayersUseCase):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayersViewModel(useCase) as T
    }
}