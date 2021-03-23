package com.nistruct.sportstat.hilt

import com.nistruct.sportstat.repository.PlayerRepository
import com.nistruct.sportstat.repository.PlayerRepositoryImpl
import com.nistruct.sportstat.ui.enter_player.EnterPlayerActivity
import com.nistruct.sportstat.ui.enter_player.EnterPlayerViewModelFactory
import com.nistruct.sportstat.ui.players.PlayersViewModelFactory
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
class EnterPlayerActivityModule {
    @Singleton
    @Provides
    fun provideCoroutineDispatcher():CoroutineDispatcher{
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun providePlayerRepository():PlayerRepository{
        return PlayerRepositoryImpl()
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
}