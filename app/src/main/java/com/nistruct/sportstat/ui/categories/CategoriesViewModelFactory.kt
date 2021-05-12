package com.nistruct.sportstat.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.usecase.category.GetCategoriesUseCase
import javax.inject.Inject

class CategoriesViewModelFactory
@Inject
constructor(private val useCase: GetCategoriesUseCase):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoriesViewModel(useCase) as T
    }
}