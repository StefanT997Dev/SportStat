package com.nistruct.sportstat.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistruct.sportstat.data.models.ui_models.Trainer
import com.nistruct.sportstat.usecase.LoginUseCaseRequest
import com.nistruct.sportstat.usecase.RegisterUseCaseRequest
import com.nistruct.sportstat.usecase.RegisterUserUseCase
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel
@Inject
constructor(
    private val useCase: RegisterUserUseCase
) : ViewModel(), RegisterViewModelContract {
    override val trainerLiveDataResult: MutableLiveData<DataResult<Trainer>> = MutableLiveData()

    lateinit var trainerUsername: String
    lateinit var trainerEmail: String
    lateinit var trainerPassword: String

    override fun enterTrainer() {
        viewModelScope.launch {
            useCase.execute(
                RegisterUseCaseRequest(trainerUsername,trainerEmail, trainerPassword)
            )
                .collect {
                    trainerLiveDataResult.value = it
                }
        }
    }
}