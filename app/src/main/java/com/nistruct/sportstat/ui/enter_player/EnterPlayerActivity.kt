package com.nistruct.sportstat.ui.enter_player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.R
import com.nistruct.sportstat.repository.PlayerRepositoryImpl
import com.nistruct.sportstat.ui.players.PlayersViewModel
import com.nistruct.sportstat.ui.players.PlayersViewModelFactory
import com.nistruct.sportstat.usecase.GetPlayersUseCaseImpl
import kotlinx.android.synthetic.main.activity_enter_player.*
import kotlinx.android.synthetic.main.activity_enter_player.playerImageView
import kotlinx.android.synthetic.main.activity_player_list_item.*
import kotlinx.coroutines.Dispatchers

class EnterPlayerActivity : AppCompatActivity() {
    private lateinit var viewModel: EnterPlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_player)


        val repository = PlayerRepositoryImpl()
        val viewModelFactory = PlayersViewModelFactory(
            GetPlayersUseCaseImpl(
                repository,
                Dispatchers.IO
            )
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(EnterPlayerViewModel::class.java)

        setPlayer()
    }

    private fun setPlayer(){
        viewModel.playerName=playerNameEditText.text.toString()
        viewModel.playerEmail=playerEmailEditText.text.toString()
        viewModel.playerImage=playerImageView.toString()
        viewModel.playerPhoneNumber=playerPhoneNumberEditText.text.toString()
        viewModel.playerPosition=playerPositionTextView.text.toString()
    }
}