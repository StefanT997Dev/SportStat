package com.nistruct.sportstat.usecase.category

import com.nistruct.sportstat.data.models.ui_models.Category
import com.nistruct.sportstat.repository.category.CategoryRepository
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetCategoriesUseCase {
    suspend fun execute(): Flow<DataResult<List<Category>>>
}

class GetCategoriesUseCaseImpl
@Inject
constructor(
    private val categoryRepository: CategoryRepository,
    private val coroutineDispatcher: CoroutineDispatcher
):GetCategoriesUseCase{
    // also offloading data off main thread in case the caller forgot to launch it on a different dispatcher
    override suspend fun execute() = withContext(coroutineDispatcher) {
        categoryRepository
            .getCategories()
            .map { categories -> DataResult.Success(categories) }
    }
}