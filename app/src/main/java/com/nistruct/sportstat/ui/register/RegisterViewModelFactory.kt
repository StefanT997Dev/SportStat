package com.nistruct.sportstat.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.usecase.RegisterUserUseCase
import javax.inject.Inject

class RegisterViewModelFactory
@Inject
constructor(private val useCase: RegisterUserUseCase):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterViewModel(useCase) as T
    }
}