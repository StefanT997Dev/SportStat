package com.nistruct.sportstat.player_recycler_view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.R
import com.nistruct.sportstat.repository.Repository

class PlayersActivity : AppCompatActivity() {
    private lateinit var viewModel: PlayersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_recycler_view)

        val repository = Repository()
        val viewModelFactory = PlayersViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(PlayersViewModel::class.java)
        viewModel.getPlayers()
        viewModel.listOfPlayersResponse.observe(this, Observer {listOfPlayersResponse->
            for(player in listOfPlayersResponse){
                Log.d("Response",player.name)
                Log.d("Response",player.position)
                Log.d("Response",player.photo)
            }
        })
    }
}