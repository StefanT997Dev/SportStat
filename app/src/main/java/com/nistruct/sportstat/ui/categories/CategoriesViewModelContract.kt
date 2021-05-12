package com.nistruct.sportstat.ui.categories

import androidx.lifecycle.LiveData
import com.nistruct.sportstat.data.models.ui_models.Category
import com.nistruct.sportstat.usecase.result.DataResult

interface CategoriesViewModelContract {
    val categoryLiveData: LiveData<DataResult<List<Category>>>

    fun getCategories()

    fun enterStatistics()
}