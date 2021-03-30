package com.nistruct.sportstat.ui.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.usecase.statistics.GetStatisticsUseCase
import javax.inject.Inject

class StatisticsViewModelFactory
@Inject
constructor(private val useCase: GetStatisticsUseCase):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StatisticsViewModel(useCase) as T
    }
}