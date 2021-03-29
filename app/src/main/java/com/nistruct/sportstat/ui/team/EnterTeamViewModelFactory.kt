package com.nistruct.sportstat.ui.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.usecase.team.PostTeamUseCase
import javax.inject.Inject

class EnterTeamViewModelFactory
@Inject
constructor(private val useCase: PostTeamUseCase):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EnterTeamViewModel(useCase) as T
    }
}