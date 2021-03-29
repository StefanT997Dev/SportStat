package com.nistruct.sportstat.ui.team

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistruct.sportstat.data.models.ui_models.Team
import com.nistruct.sportstat.usecase.PostPlayerUseCaseRequest
import com.nistruct.sportstat.usecase.result.DataResult
import com.nistruct.sportstat.usecase.team.PostTeamUseCase
import com.nistruct.sportstat.usecase.team.PostTeamUseCaseRequest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class EnterTeamViewModel
@Inject
constructor(
    private val useCase: PostTeamUseCase
):ViewModel(),EnterTeamViewModelContract {
    override val teamLiveData: MutableLiveData<DataResult<Team>> = MutableLiveData()

    lateinit var teamLogo: String
    lateinit var teamName: String

    override fun enterTeam() {
        viewModelScope.launch {
            useCase.execute(
                PostTeamUseCaseRequest(
                    teamLogo,
                    teamName
                )
            ).collect { result ->
                teamLiveData.value = result
            }
        }
    }
}