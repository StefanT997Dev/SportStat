package com.nistruct.sportstat.usecase.statistics

import com.nistruct.sportstat.data.models.ui_models.Statistics
import com.nistruct.sportstat.repository.statistics.StatisticsRepository
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetStatisticsUseCase {
    suspend fun execute(): Flow<DataResult<List<Statistics>>>
}

class GetStatisticsUseCaseImpl
@Inject
constructor(
    private val statisticsRepository: StatisticsRepository,
    private val coroutineDispatcher: CoroutineDispatcher
):GetStatisticsUseCase{
    // also offloading data off main thread in case the caller forgot to launch it on a different dispatcher
    override suspend fun execute() = withContext(coroutineDispatcher) {
        statisticsRepository
            .getStatistics()
            .map { statistics -> DataResult.Success(statistics) }
    }
}