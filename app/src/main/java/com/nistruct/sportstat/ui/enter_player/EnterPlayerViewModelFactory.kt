package com.nistruct.sportstat.ui.enter_player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.usecase.PostPlayerUseCase

class EnterPlayerViewModelFactory(private val useCase: PostPlayerUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EnterPlayerViewModel(useCase) as T
    }
}