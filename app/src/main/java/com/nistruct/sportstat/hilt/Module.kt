package com.nistruct.sportstat.hilt

import com.nistruct.sportstat.data.mappers.PlayerResponseToPlayerMapper
import com.nistruct.sportstat.data.mappers.UserResponseToPlayerMapper
import com.nistruct.sportstat.repository.PlayerRepository
import com.nistruct.sportstat.repository.PlayerRepositoryImpl
import com.nistruct.sportstat.ui.enter_player.EnterPlayerActivity
import com.nistruct.sportstat.ui.enter_player.EnterPlayerViewModelFactory
import com.nistruct.sportstat.ui.players.PlayersViewModelFactory
import com.nistruct.sportstat.usecase.GetPlayersUseCase
import com.nistruct.sportstat.usecase.GetPlayersUseCaseImpl
import com.nistruct.sportstat.usecase.PostPlayerUseCase
import com.nistruct.sportstat.usecase.PostPlayerUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Module {
    @Singleton
    @Provides
    fun provideUserResponseToPlayerMapper():UserResponseToPlayerMapper{
        return UserResponseToPlayerMapper()
    }

    @Singleton
    @Provides
    fun provideCoroutineDispatcher():CoroutineDispatcher{
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun providePlayerRepository(userResponseToPlayerMapper: UserResponseToPlayerMapper):PlayerRepository{
        return PlayerRepositoryImpl(userResponseToPlayerMapper)
    }

    @Singleton
    @Provides
    fun provideUseCase(playerRepository: PlayerRepository,coroutineDispatcher: CoroutineDispatcher):PostPlayerUseCase{
        return PostPlayerUseCaseImpl(playerRepository,coroutineDispatcher)
    }

    @Singleton
    @Provides
    fun provideEnterPlayerViewModelFactory(useCase:PostPlayerUseCase):EnterPlayerViewModelFactory{
        return EnterPlayerViewModelFactory(useCase)
    }

    // PLAYER ACTIVITY PROVIDES
    @Singleton
    @Provides
    fun provideUseCaseForPlayersActivity(playerRepository: PlayerRepository,coroutineDispatcher: CoroutineDispatcher):GetPlayersUseCase{
        return GetPlayersUseCaseImpl(playerRepository,coroutineDispatcher)
    }
}