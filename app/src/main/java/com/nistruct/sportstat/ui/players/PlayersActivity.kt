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
import com.nistruct.sportstat.usecase.GetPlayersUseCase
import com.nistruct.sportstat.usecase.GetPlayersUseCaseImpl
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.android.synthetic.main.activity_players_recycler_view.*
import kotlinx.coroutines.Dispatchers

class PlayersActivity : AppCompatActivity() {
    private lateinit var viewModel: PlayersViewModel

    private lateinit var playerAdapter: PlayerRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_recycler_view)

        initRecyclerView()

        val repository = PlayerRepositoryImpl()
        val viewModelFactory = PlayersViewModelFactory(GetPlayersUseCaseImpl(repository,Dispatchers.IO))
        viewModel = ViewModelProvider(this,viewModelFactory).get(PlayersViewModel::class.java)
        viewModel.getPlayers()
        viewModel.playersLiveData.observe(this) {result->
            when(result){
                is DataResult.Success -> playerAdapter.submitList(result.data)
                is DataResult.Loading -> {}
                is DataResult.Error -> {}
            }
        }
    }

    private fun initRecyclerView(){
        playerRecyclerView.layoutManager = LinearLayoutManager(this@PlayersActivity)
        playerAdapter= PlayerRecyclerAdapter()
        playerRecyclerView.adapter=playerAdapter
    }
}