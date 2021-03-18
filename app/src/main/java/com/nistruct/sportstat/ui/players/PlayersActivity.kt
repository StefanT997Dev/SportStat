package com.nistruct.sportstat.ui.players

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nistruct.sportstat.R
import com.nistruct.sportstat.data.models.ui_models.Player
import com.nistruct.sportstat.repository.PlayerRepositoryImpl
import kotlinx.android.synthetic.main.activity_players_recycler_view.*

class PlayersActivity : AppCompatActivity() {
    private lateinit var viewModel: PlayersViewModel

    private lateinit var playerAdapter: PlayerRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_recycler_view)

        initRecyclerView()

        val repository = PlayerRepositoryImpl()
        val viewModelFactory = PlayersViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(PlayersViewModel::class.java)
        viewModel.getPlayers()
        viewModel.playersLiveData.observe(this) {listOfPlayers->
            playerAdapter.submitList(listOfPlayers)
        }
    }

    private fun initRecyclerView(){
        playerRecyclerView.layoutManager = LinearLayoutManager(this@PlayersActivity)
        playerAdapter= PlayerRecyclerAdapter()
        playerRecyclerView.adapter=playerAdapter
    }
}