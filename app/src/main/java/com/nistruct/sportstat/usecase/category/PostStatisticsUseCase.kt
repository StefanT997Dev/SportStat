package com.nistruct.sportstat.usecase.category

import com.nistruct.sportstat.data.models.ui_models.Category
import com.nistruct.sportstat.repository.category.CategoryRepository
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PostStatisticsUseCase {
    suspend fun execute(request: PostStatisticsUseCaseRequest): Flow<DataResult.Success<Category>>
}

data class PostStatisticsUseCaseRequest(
    val name: String
)

class PostStatisticsUseCaseImpl
@Inject
constructor(
    private val categoryRepository: CategoryRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : PostStatisticsUseCase {
    override suspend fun execute(request: PostStatisticsUseCaseRequest) =
        withContext(coroutineDispatcher) {
            categoryRepository
                .postStatistics(
                    request.name
                )
                .map { statistic -> DataResult.Success(statistic) }
        }
}