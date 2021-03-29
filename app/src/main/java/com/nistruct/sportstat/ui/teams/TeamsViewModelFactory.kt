package com.nistruct.sportstat.ui.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.usecase.GetPlayersUseCase
import com.nistruct.sportstat.usecase.team.GetTeamsUseCase

class TeamsViewModelFactory(private val useCase: GetTeamsUseCase):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TeamsViewModel(useCase) as T
    }
}