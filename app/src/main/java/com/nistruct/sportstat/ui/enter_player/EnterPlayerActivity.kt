package com.nistruct.sportstat.ui.enter_player

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.R
import com.nistruct.sportstat.repository.PlayerRepositoryImpl
import com.nistruct.sportstat.ui.team.EnterTeamViewModel
import com.nistruct.sportstat.usecase.PostPlayerUseCaseImpl
import com.nistruct.sportstat.usecase.result.DataResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_enter_player.*
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@AndroidEntryPoint
class EnterPlayerActivity: AppCompatActivity() {
    @Inject lateinit var enterPlayerViewModelFactory:EnterPlayerViewModelFactory

    private lateinit var viewModel: EnterPlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_player)

        viewModel = ViewModelProvider(this, enterPlayerViewModelFactory).get(EnterPlayerViewModel::class.java)

        savePlayerButton.setOnClickListener {
            setPlayer()
            viewModel.enterPlayer()
        }

        viewModel.playerLiveData.observe(this) { result ->
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

    private fun setPlayer() {
        viewModel.playerName = playerNameEditText.text.toString()
        viewModel.playerEmail = playerEmailEditText.text.toString()
        viewModel.playerImage = playerImageView.toString()
        viewModel.playerPhoneNumber = playerPhoneNumberEditText.text.toString()
        viewModel.playerPosition = roleOfPlayerSpinner.selectedItem.toString()
    }
}