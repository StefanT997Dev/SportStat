package com.nistruct.sportstat.ui.statistics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistruct.sportstat.data.models.ui_models.Statistics
import com.nistruct.sportstat.usecase.result.DataResult
import com.nistruct.sportstat.usecase.statistics.GetStatisticsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class StatisticsViewModel
@Inject
constructor(
    private val useCase: GetStatisticsUseCase
) :ViewModel(),StatisticsViewModelContract {
    override val statisticsLiveData: MutableLiveData<DataResult<List<Statistics>>> = MutableLiveData()

    override fun getStatistics() {
        viewModelScope.launch {
            useCase.execute().onStart {
                emit(DataResult.Loading)
            }.collect { data ->
                statisticsLiveData.value = data
            }

            // When posting data on the UI it has to be switched to a Main thread using withContext(Dispatchers.Main)
        }
    }
}