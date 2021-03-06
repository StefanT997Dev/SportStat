package com.nistruct.sportstat.ui.categories

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.R
import com.nistruct.sportstat.usecase.result.DataResult
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesActivity:AppCompatActivity() {
    @Inject
    lateinit var categoriesViewModelFactory: CategoriesViewModelFactory

    private lateinit var viewModel: CategoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_statistics)

        viewModel = ViewModelProvider(this, categoriesViewModelFactory).get(CategoriesViewModel::class.java)

        viewModel.getCategories()

        viewModel.categoryLiveData.observe(this){ result ->
            when (result) {
                is DataResult.Success -> {
                    Log.d("Response", result.data[0].name)
                }
                is DataResult.Error -> {
                    Log.d("Response", "Error bro")
                }
                is DataResult.Loading -> {
                    // Implement spinner
                }
            }
        }
    }
}