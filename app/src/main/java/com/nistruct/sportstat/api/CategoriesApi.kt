package com.nistruct.sportstat.api

import com.nistruct.sportstat.data.models.api_models.PostStatisticsRequestBody
import com.nistruct.sportstat.data.models.api_models.CategoryResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoriesApi {
    @GET("categories")
    suspend fun getCategories(): List<CategoryResponse>

    @POST("api/statistics")
    suspend fun postStatistics(@Body statisticsRequestBody: PostStatisticsRequestBody): CategoryResponse
}