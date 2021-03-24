package com.nistruct.sportstat.usecase

import com.nistruct.sportstat.data.models.api_models.LoginUserRequestBody
import com.nistruct.sportstat.data.models.api_models.UserResponse
import com.nistruct.sportstat.data.models.ui_models.Trainer
import com.nistruct.sportstat.repository.login.UserRepository
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface LoginUserUseCase{
    suspend fun execute(request:LoginUseCaseRequest): Flow<DataResult.Success<Trainer>>
}

data class LoginUseCaseRequest(
    val email:String,
    val password:String
)

class LoginUserUseCaseImpl
@Inject
constructor(
    private val userRepository: UserRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) :LoginUserUseCase{
    override suspend fun execute(request: LoginUseCaseRequest)=
        withContext(coroutineDispatcher){
            userRepository.loginUser(
                request.email,
                request.password
            )
        }
            .map {trainer->DataResult.Success(trainer)}
}