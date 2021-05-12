package com.nistruct.sportstat.ui.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistruct.sportstat.data.models.ui_models.Category
import com.nistruct.sportstat.usecase.category.GetCategoriesUseCase
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoriesViewModel
@Inject
constructor(
    private val useCase: GetCategoriesUseCase
) :ViewModel(),CategoriesViewModelContract {
    override val categoryLiveData: MutableLiveData<DataResult<List<Category>>> = MutableLiveData()

    override fun getCategories() {
        viewModelScope.launch {
            useCase.execute().onStart {
                emit(DataResult.Loading)
            }.collect { data->
                categoryLiveData.value=data
            }

            // When posting data on the UI it has to be switched to a Main thread using withContext(Dispatchers.Main)
        }
    }

    override fun enterStatistics() {
        viewModelScope.launch {
            useCase.execute(

            )
        }
    }
}