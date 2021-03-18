package com.nistruct.sportstat.ui.players

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistruct.sportstat.data.models.api_models.PlayerResponse
import com.nistruct.sportstat.data.models.ui_models.Player
import com.nistruct.sportstat.repository.PlayerRepository
import com.nistruct.sportstat.usecase.GetPlayersUseCase
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayersViewModel(private val useCase: GetPlayersUseCase):ViewModel(), PlayersViewModelContract {
    override val playersLiveData: MutableLiveData<DataResult<List<Player>>> = MutableLiveData()

    override fun getPlayers(){
        viewModelScope.launch {
            useCase.execute().onStart {
                emit(DataResult.Loading)
            }.collect { data->
                    playersLiveData.value = data
            }

            // When posting data on the UI it has to be switched to a Main thread using withContext(Dispatchers.Main)
        }
    }
}