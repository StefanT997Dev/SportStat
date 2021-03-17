package com.nistruct.sportstat.ui.players

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.R
import com.nistruct.sportstat.repository.PlayerRepositoryImpl

class PlayersActivity : AppCompatActivity() {
    private lateinit var viewModel: PlayersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_recycler_view)

        val repository = PlayerRepositoryImpl()
        val viewModelFactory = PlayersViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(PlayersViewModel::class.java)
        viewModel.getPlayers()
        viewModel.playersLiveData.observe(this, Observer {listOfPlayersResponse->
            for(player in listOfPlayersResponse){
                Log.d("Response",player.name)
                Log.d("Response",player.position)
                Log.d("Response",player.photo)
            }
        })
    }
}