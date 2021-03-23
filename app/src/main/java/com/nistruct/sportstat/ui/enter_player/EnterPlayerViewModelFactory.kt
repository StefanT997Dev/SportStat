package com.nistruct.sportstat.ui.enter_player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.usecase.PostPlayerUseCase
import javax.inject.Inject

class EnterPlayerViewModelFactory
@Inject
constructor(private val useCase: PostPlayerUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EnterPlayerViewModel(useCase) as T
    }
}