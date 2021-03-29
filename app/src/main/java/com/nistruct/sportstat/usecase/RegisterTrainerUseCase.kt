package com.nistruct.sportstat.usecase

import com.nistruct.sportstat.data.models.ui_models.Trainer
import com.nistruct.sportstat.repository.login.UserRepository
import com.nistruct.sportstat.repository.register.UserRegisterRepository
import com.nistruct.sportstat.usecase.result.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RegisterUserUseCase{
    suspend fun execute(request:RegisterUseCaseRequest): Flow<DataResult.Success<Trainer>>
}

data class RegisterUseCaseRequest(
    val username:String,
    val email:String,
    val password:String
)

class RegisterUserUseCaseImpl
@Inject
constructor(
    private val userRegisterRepository: UserRegisterRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) :RegisterUserUseCase{
    override suspend fun execute(request: RegisterUseCaseRequest)=
        withContext(coroutineDispatcher){
            userRegisterRepository.registerUser(
                request.username,
                request.email,
                request.password
            )
        }
            .map {trainer-> DataResult.Success(trainer)}
}