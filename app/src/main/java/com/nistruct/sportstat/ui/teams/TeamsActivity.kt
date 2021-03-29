package com.nistruct.sportstat.ui.teams

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.R
import com.nistruct.sportstat.ui.register.RegisterViewModel
import com.nistruct.sportstat.ui.register.RegisterViewModelFactory
import com.nistruct.sportstat.usecase.result.DataResult
import javax.inject.Inject

class TeamsActivity : AppCompatActivity() {
    @Inject
    lateinit var teamsViewModelFactory: TeamsViewModelFactory

    private lateinit var viewModel: TeamsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)

        viewModel = ViewModelProvider(this, teamsViewModelFactory).get(TeamsViewModel::class.java)

        viewModel.getTeams()
        viewModel.teamsLiveData.observe(this) { result->
            when(result){
                is DataResult.Success -> {
                    TODO()
                }
                is DataResult.Loading -> {
                    TODO()
                }
                is DataResult.Error -> {
                    TODO()
                }
            }
        }
    }
}