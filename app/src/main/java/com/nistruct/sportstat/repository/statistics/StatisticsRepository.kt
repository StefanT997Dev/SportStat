package com.nistruct.sportstat.repository.statistics

import com.nistruct.sportstat.data.models.ui_models.Player
import com.nistruct.sportstat.data.models.ui_models.Statistics
import kotlinx.coroutines.flow.Flow

interface StatisticsRepository {
    suspend fun getStatistics(): Flow<List<Statistics>>

    suspend fun postStatistics(
        name:String
    ): Flow<Statistics>
}