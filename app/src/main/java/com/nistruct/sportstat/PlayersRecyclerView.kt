package com.nistruct.sportstat

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.repository.Repository

class PlayersRecyclerView : AppCompatActivity() {
    private lateinit var viewModel:PlayersRecyclerViewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_recycler_view)

        val repository = Repository()
        val viewModelFactory = PlayersRecyclerViewViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(PlayersRecyclerViewViewModel::class.java)
        viewModel.getPlayers()
        viewModel.listOfPlayersResponse.observe(this, Observer {response->
            Log.d("Response",response[0].name)
            Log.d("Response",response[0].position)
            Log.d("Response",response[0].photo)
        })
    }
}