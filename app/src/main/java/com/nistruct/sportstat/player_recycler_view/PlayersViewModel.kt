package com.nistruct.sportstat.player_recycler_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistruct.sportstat.data.models.api_models.User
import com.nistruct.sportstat.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlayersViewModel(private val repository: Repository):ViewModel() {
    val listOfPlayersResponse: MutableLiveData<List<User>> = MutableLiveData()

    fun getPlayers(){
        viewModelScope.launch {
            repository.getPlayers().collect { data->
                listOfPlayersResponse.value = data
            }
        }
    }
}