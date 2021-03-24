package com.nistruct.sportstat.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.usecase.LoginUserUseCase
import javax.inject.Inject

class LoginViewModelFactory
@Inject
constructor(private val useCase:LoginUserUseCase):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(useCase) as T
    }

}
