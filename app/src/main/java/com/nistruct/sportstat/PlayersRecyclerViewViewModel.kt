package com.nistruct.sportstat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistruct.sportstat.data.models.User
import com.nistruct.sportstat.repository.Repository
import kotlinx.coroutines.launch

class PlayersRecyclerViewViewModel(private val repository: Repository):ViewModel() {
    val listOfPlayersResponse: MutableLiveData<List<User>> = MutableLiveData()

    fun getPlayers(){
        viewModelScope.launch {
            val response:List<User> = repository.getPlayers()
            listOfPlayersResponse.value = response
        }
    }
}