package com.nistruct.sportstat.repository.category

import com.nistruct.sportstat.api.RetrofitInstance
import com.nistruct.sportstat.data.mappers.CategoryResponseToCategoryMapper
import com.nistruct.sportstat.data.models.api_models.PostStatisticsRequestBody
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl
@Inject
constructor(
    private val categoryResponseToCategoryMapper: CategoryResponseToCategoryMapper
) :CategoryRepository {
    override suspend fun getCategories() = flow {
        emit(RetrofitInstance.categoriesApi.getCategories())
    }
        .map { categoriesResponseList ->
            categoriesResponseList.filter { categoriesResponseList.isNotEmpty() }
                .map { categoryResponse ->
                    categoryResponseToCategoryMapper.map(categoryResponse)
                }
        }

    override suspend fun postStatistics(name: String)= flow {
        val postStatisticsRequest = PostStatisticsRequestBody(
            name
        )

        emit(RetrofitInstance.categoriesApi.postStatistics(postStatisticsRequest))
    }
        .map { statisticsResponse ->
            categoryResponseToCategoryMapper.map(statisticsResponse)
        }
}