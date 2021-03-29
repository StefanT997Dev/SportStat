package com.nistruct.sportstat.ui.teams

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistruct.sportstat.data.models.ui_models.Team
import com.nistruct.sportstat.usecase.result.DataResult
import com.nistruct.sportstat.usecase.team.GetTeamsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamsViewModel
@Inject
constructor(private val useCase: GetTeamsUseCase): ViewModel(),TeamsViewModelContract {
    override val teamsLiveData: MutableLiveData<DataResult<List<Team>>> = MutableLiveData()

    override fun getTeams() {
        viewModelScope.launch {
            useCase.execute().onStart {
                emit(DataResult.Loading)
            }.collect { data ->
                teamsLiveData.value = data
            }

            // When posting data on the UI it has to be switched to a Main thread using withContext(Dispatchers.Main)
        }
    }
}