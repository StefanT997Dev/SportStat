package com.nistruct.sportstat.repository.statistics

import com.nistruct.sportstat.api.RetrofitInstance
import com.nistruct.sportstat.data.mappers.StatisticsResponseToStatisticsMapper
import com.nistruct.sportstat.data.models.api_models.PostPlayerRequestBody
import com.nistruct.sportstat.data.models.api_models.PostStatisticsRequestBody
import com.nistruct.sportstat.data.models.ui_models.Statistics
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StatisticsRepositoryImpl
@Inject
constructor(
    private val statisticsResponseToStatisticsMapper: StatisticsResponseToStatisticsMapper
) :StatisticsRepository {
    override suspend fun getStatistics() = flow {
        emit(RetrofitInstance.statisticsApi.getStatistics())
    }
        .map { statisticsResponseList ->
            statisticsResponseList.filter { statisticsResponseList.isNotEmpty() }
                .map { statisticsResponse ->
                    statisticsResponseToStatisticsMapper.map(statisticsResponse)
                }
        }

    override suspend fun postStatistics(name: String)= flow {
        val postStatisticsRequest = PostStatisticsRequestBody(
            name
        )

        emit(RetrofitInstance.statisticsApi.postStatistics(postStatisticsRequest))
    }
        .map { statisticsResponse ->
            statisticsResponseToStatisticsMapper.map(statisticsResponse)
        }
}