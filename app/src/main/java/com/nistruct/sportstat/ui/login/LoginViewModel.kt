package com.nistruct.sportstat.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistruct.sportstat.data.models.ui_models.Trainer
import com.nistruct.sportstat.usecase.LoginUseCaseRequest
import com.nistruct.sportstat.usecase.LoginUserUseCase
import com.nistruct.sportstat.usecase.PostPlayerUseCase
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel
@Inject
constructor(
    private val useCase: LoginUserUseCase
):ViewModel(),LoginViewModelContract {
    override val trainerLiveDataResult: MutableLiveData<DataResult<Trainer>> = MutableLiveData()

    lateinit var trainerEmail:String
    lateinit var trainerPassword:String

    override fun enterTrainer() {
        viewModelScope.launch {
            useCase.execute(
                LoginUseCaseRequest(trainerEmail,trainerPassword)
            )
                .collect {
                    trainerLiveDataResult.value = it
                }
        }
    }
}