package com.nistruct.sportstat.ui.statistics

import androidx.lifecycle.LiveData
import com.nistruct.sportstat.data.models.ui_models.Statistics
import com.nistruct.sportstat.usecase.result.DataResult

interface StatisticsViewModelContract {
    val statisticsLiveData: LiveData<DataResult<List<Statistics>>>

    fun getStatistics()
}