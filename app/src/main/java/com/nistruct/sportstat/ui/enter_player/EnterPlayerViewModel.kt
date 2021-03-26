package com.nistruct.sportstat.ui.enter_player

import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistruct.sportstat.data.models.ui_models.Player
import com.nistruct.sportstat.usecase.PostPlayerUseCase
import com.nistruct.sportstat.usecase.PostPlayerUseCaseRequest
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class EnterPlayerViewModel
@Inject
constructor(
    private val useCase: PostPlayerUseCase
) : ViewModel(),
    EnterPlayerViewModelContract {
    override val playerLiveData: MutableLiveData<DataResult<Player>> = MutableLiveData()

    lateinit var playerName: String
    lateinit var playerEmail: String
    lateinit var playerPhoneNumber: String
    lateinit var playerPosition: String
    lateinit var playerImage: String

    override fun enterPlayer() {
        viewModelScope.launch {
            useCase.execute(
                PostPlayerUseCaseRequest(
                    playerName,
                    playerEmail,
                    playerPhoneNumber,
                    playerPosition,
                    playerImage
                )
            ).collect { result ->
                playerLiveData.value = result
            }
        }
    }

    fun validatePlayerNameEntry(playerName:String):Boolean{
        return playerName != ""
    }
}