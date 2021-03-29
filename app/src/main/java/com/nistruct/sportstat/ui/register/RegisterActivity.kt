package com.nistruct.sportstat.ui.register

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.R
import com.nistruct.sportstat.usecase.result.DataResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    @Inject
    lateinit var registerViewModelFactory: RegisterViewModelFactory

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewModel = ViewModelProvider(this, registerViewModelFactory).get(RegisterViewModel::class.java)

        registerButton.setOnClickListener{
            setTrainer()
            viewModel.enterTrainer()
        }

        viewModel.trainerLiveDataResult.observe(this){ result ->
            when (result) {
                is DataResult.Success -> {
                    Log.d("Response", result.data.email)
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

    private fun setTrainer() {
        viewModel.trainerUsername=usernameEditText.text.toString()
        viewModel.trainerEmail=emailEditText.text.toString()
        viewModel.trainerPassword=passwordEditText.text.toString()
    }
}