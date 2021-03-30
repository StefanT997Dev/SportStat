package com.nistruct.sportstat.api

import com.nistruct.sportstat.data.models.api_models.PostStatisticsRequestBody
import com.nistruct.sportstat.data.models.api_models.StatisticsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface StatisticsApi {
    @GET("api/statistics")
    suspend fun getStatistics(): List<StatisticsResponse>

    @POST("api/statistics")
    suspend fun postStatistics(@Body statisticsRequestBody: PostStatisticsRequestBody): StatisticsResponse
}