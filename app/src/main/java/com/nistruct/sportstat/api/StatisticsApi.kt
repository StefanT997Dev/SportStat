package com.nistruct.sportstat.api

import com.nistruct.sportstat.data.models.api_models.StatisticsResponse
import retrofit2.http.GET

interface StatisticsApi {
    @GET("api/statistics")
    suspend fun getStatistics(): List<StatisticsResponse>
}