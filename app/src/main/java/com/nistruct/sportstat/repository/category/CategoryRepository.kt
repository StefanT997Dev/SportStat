package com.nistruct.sportstat.repository.category

import com.nistruct.sportstat.data.models.ui_models.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<List<Category>>

    suspend fun postStatistics(
        name:String
    ): Flow<Category>
}