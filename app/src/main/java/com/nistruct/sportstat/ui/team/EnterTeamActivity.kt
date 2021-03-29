package com.nistruct.sportstat.ui.team

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.R
import com.nistruct.sportstat.usecase.result.DataResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_enter_team.*
import javax.inject.Inject

@AndroidEntryPoint
class EnterTeamActivity:AppCompatActivity() {
    @Inject
    lateinit var enterTeamViewModelFactory: EnterTeamViewModelFactory

    private lateinit var viewModel: EnterTeamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_team)

        viewModel = ViewModelProvider(this, enterTeamViewModelFactory).get(EnterTeamViewModel::class.java)

        saveTeamButton.setOnClickListener {
            setTeam()
            viewModel.enterTeam()
        }

        viewModel.teamLiveData.observe(this){ result ->
            when (result) {
                is DataResult.Success -> {
                    Log.d("Response", result.data.name)
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

    private fun setTeam() {
        viewModel.teamLogo=teamImageView.toString()
        viewModel.teamName=teamNameEditText.text.toString()
    }
}