package com.nistruct.sportstat.ui.team

import androidx.lifecycle.LiveData
import com.nistruct.sportstat.data.models.ui_models.Team
import com.nistruct.sportstat.usecase.result.DataResult

interface EnterTeamViewModelContract {
    val teamLiveData: LiveData<DataResult<Team>>

    fun enterTeam()
}