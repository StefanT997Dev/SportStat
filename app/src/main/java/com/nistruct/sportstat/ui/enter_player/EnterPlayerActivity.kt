package com.nistruct.sportstat.ui.enter_player

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.R
import com.nistruct.sportstat.repository.PlayerRepositoryImpl
import com.nistruct.sportstat.usecase.PostPlayerUseCaseImpl
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.android.synthetic.main.activity_enter_player.*
import kotlinx.android.synthetic.main.activity_enter_player.playerImageView
import kotlinx.coroutines.Dispatchers

class EnterPlayerActivity : AppCompatActivity() {
    private lateinit var viewModel: EnterPlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_player)

        val repository = PlayerRepositoryImpl()
        val viewModelFactory = EnterPlayerViewModelFactory(
            PostPlayerUseCaseImpl(
                repository,
                Dispatchers.IO
            )
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(EnterPlayerViewModel::class.java)

        setPlayer()

        savePlayerButton.setOnClickListener{
            viewModel.enterPlayer()
        }

        viewModel.playerLiveData.observe(this){result->
            when(result){
                is DataResult.Success->{ Log.d("Response",result.data.name)}
                is DataResult.Error->{Log.d("Response","Error bro")}
                is DataResult.Loading-> {
                    // Implement spinner
                }
            }
        }
    }

    private fun setPlayer(){
        viewModel.playerName=playerNameEditText.text.toString()
        viewModel.playerEmail=playerEmailEditText.text.toString()
        viewModel.playerImage=playerImageView.toString()
        viewModel.playerPhoneNumber=playerPhoneNumberEditText.text.toString()
        viewModel.playerPosition=roleOfPlayerSpinner.selectedItem.toString()
    }
}