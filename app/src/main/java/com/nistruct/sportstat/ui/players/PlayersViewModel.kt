package com.nistruct.sportstat.ui.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistruct.sportstat.data.models.api_models.PlayerResponse
import com.nistruct.sportstat.data.models.ui_models.Player
import com.nistruct.sportstat.repository.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayersViewModel(private val repository: PlayerRepository):ViewModel(), PlayersViewModelContract {
    private val listOfPlayersRes: MutableLiveData<List<Player>> = MutableLiveData()
    override val playersLiveData: LiveData<List<Player>>
        get() = listOfPlayersRes

    override fun getPlayers(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPlayers().collect { data->
                withContext(Dispatchers.Main){
                    listOfPlayersRes.value = data
                }
            }

            // When posting data on the UI it has to be switched to a Main thread using withContext(Dispatchers.Main)
        }
    }
}