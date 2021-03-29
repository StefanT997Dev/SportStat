package com.nistruct.sportstat.ui.players

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.material.internal.ContextUtils.getActivity
import com.nistruct.sportstat.R
import com.nistruct.sportstat.repository.PlayerRepositoryImpl
import com.nistruct.sportstat.usecase.GetPlayersUseCaseImpl
import com.nistruct.sportstat.usecase.result.DataResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_players_recycler_view.*
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@AndroidEntryPoint
class PlayersActivity : AppCompatActivity() {
    @Inject lateinit var playersViewModelFactory:PlayersViewModelFactory

    private lateinit var viewModel: PlayersViewModel

    private lateinit var playerAdapter: PlayerRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_recycler_view)

        initRecyclerView()

        viewModel = ViewModelProvider(this, playersViewModelFactory).get(PlayersViewModel::class.java)
        viewModel.getPlayers()
        viewModel.playersLiveData.observe(this) { result->
            when(result){
                is DataResult.Success -> playerAdapter.submitList(result.data)
                is DataResult.Loading -> {
                }
                is DataResult.Error -> {
                }
            }
        }

        // Google sign in
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            val personEmail = acct.email
            val personId = acct.id
            val personPhoto: Uri? = acct.photoUrl
        }
    }

    private fun initRecyclerView(){
        playerRecyclerView.layoutManager = LinearLayoutManager(this@PlayersActivity)
        playerAdapter= PlayerRecyclerAdapter()
        playerRecyclerView.adapter=playerAdapter
    }
}