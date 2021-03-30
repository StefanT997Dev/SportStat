package com.nistruct.sportstat.usecase.statistics

import com.nistruct.sportstat.data.models.ui_models.Statistics
import com.nistruct.sportstat.repository.statistics.StatisticsRepository
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PostStatisticsUseCase {
    suspend fun execute(request: PostStatisticsUseCaseRequest): Flow<DataResult.Success<Statistics>>
}

data class PostStatisticsUseCaseRequest(
    val name: String
)

class PostStatisticsUseCaseImpl
@Inject
constructor(
    private val statisticsRepository: StatisticsRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : PostStatisticsUseCase {
    override suspend fun execute(request: PostStatisticsUseCaseRequest) =
        withContext(coroutineDispatcher) {
            statisticsRepository
                .postStatistics(
                    request.name
                )
                .map { statistic -> DataResult.Success(statistic) }
        }
}