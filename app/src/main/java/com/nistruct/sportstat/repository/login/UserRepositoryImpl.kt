package com.nistruct.sportstat.repository.login

import com.nistruct.sportstat.api.RetrofitInstance
import com.nistruct.sportstat.data.mappers.UserResponseToTrainerMapper
import com.nistruct.sportstat.data.models.api_models.LoginUserRequestBody
import com.nistruct.sportstat.data.models.api_models.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl
@Inject
constructor(
    private val userResponseToTrainerMapper: UserResponseToTrainerMapper
):UserRepository {
    override fun loginUser(trainerEmail:String, trainerPassword:String)=flow{
        val loginUserRequestBody=LoginUserRequestBody(
            trainerEmail,trainerPassword
        )
        emit(RetrofitInstance.authApi.loginTrainer(loginUserRequestBody))
    }
        .map{userResponse->
            userResponseToTrainerMapper.map(userResponse)
        }
}