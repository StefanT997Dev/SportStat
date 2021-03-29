package com.nistruct.sportstat.repository.register

import com.nistruct.sportstat.api.RetrofitInstance
import com.nistruct.sportstat.data.mappers.UserResponseToTrainerMapper
import com.nistruct.sportstat.data.models.api_models.RegisterUserRequestBody
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRegisterRepositoryImpl
@Inject
constructor(
    private val userResponseToTrainerMapper: UserResponseToTrainerMapper
): UserRegisterRepository {
    override fun registerUser(username: String, email: String, password: String)= flow {
        val registerUserRequestBody=RegisterUserRequestBody(
            username,email,password
        )

        emit(RetrofitInstance.authApi.registerTrainer(registerUserRequestBody))
    }
        .map {userResponse->
            userResponseToTrainerMapper.map(userResponse)
        }
}