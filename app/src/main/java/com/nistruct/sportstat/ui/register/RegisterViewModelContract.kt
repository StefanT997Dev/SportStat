package com.nistruct.sportstat.ui.register

import androidx.lifecycle.LiveData
import com.nistruct.sportstat.data.models.ui_models.Trainer
import com.nistruct.sportstat.usecase.result.DataResult

interface RegisterViewModelContract {
    val trainerLiveDataResult: LiveData<DataResult<Trainer>>

    fun enterTrainer()
}