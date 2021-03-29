package com.nistruct.sportstat.ui.teams

import androidx.lifecycle.LiveData
import com.nistruct.sportstat.data.models.ui_models.Team
import com.nistruct.sportstat.usecase.result.DataResult

interface TeamsViewModelContract {
    val teamsLiveData: LiveData<DataResult<List<Team>>>

    fun getTeams()
}