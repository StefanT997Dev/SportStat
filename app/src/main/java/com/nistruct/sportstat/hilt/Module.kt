package com.nistruct.sportstat.hilt

import com.nistruct.sportstat.data.mappers.CategoryResponseToCategoryMapper
import com.nistruct.sportstat.data.mappers.TeamResponseToTeamMapper
import com.nistruct.sportstat.data.mappers.UserResponseToPlayerMapper
import com.nistruct.sportstat.data.mappers.UserResponseToTrainerMapper
import com.nistruct.sportstat.repository.PlayerRepository
import com.nistruct.sportstat.repository.PlayerRepositoryImpl
import com.nistruct.sportstat.repository.login.UserRepository
import com.nistruct.sportstat.repository.login.UserRepositoryImpl
import com.nistruct.sportstat.repository.register.UserRegisterRepository
import com.nistruct.sportstat.repository.register.UserRegisterRepositoryImpl
import com.nistruct.sportstat.repository.category.CategoryRepository
import com.nistruct.sportstat.repository.category.CategoryRepositoryImpl
import com.nistruct.sportstat.repository.team.TeamRepository
import com.nistruct.sportstat.repository.team.TeamRepositoryImpl
import com.nistruct.sportstat.ui.enter_player.EnterPlayerViewModelFactory
import com.nistruct.sportstat.ui.players.PlayersViewModelFactory
import com.nistruct.sportstat.ui.register.RegisterViewModelFactory
import com.nistruct.sportstat.ui.team.EnterTeamViewModelFactory
import com.nistruct.sportstat.usecase.*
import com.nistruct.sportstat.usecase.category.GetCategoriesUseCase
import com.nistruct.sportstat.usecase.category.GetCategoriesUseCaseImpl
import com.nistruct.sportstat.usecase.team.PostTeamUseCase
import com.nistruct.sportstat.usecase.team.PostTeamUseCaseImpl
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
    fun providePlayersViewModelFactory(useCase:GetPlayersUseCase):PlayersViewModelFactory{
        return PlayersViewModelFactory(useCase)
    }

    @Singleton
    @Provides
    fun provideUseCaseForPlayersActivity(playerRepository: PlayerRepository,coroutineDispatcher: CoroutineDispatcher):GetPlayersUseCase{
        return GetPlayersUseCaseImpl(playerRepository,coroutineDispatcher)
    }

    // LOGIN ACTIVITY PROVIDES
    @Singleton
    @Provides
    fun provideUserResponseToTrainerMapper():UserResponseToTrainerMapper{
        return UserResponseToTrainerMapper()
    }

    @Singleton
    @Provides
    fun provideUserRepository(userResponseToTrainerMapper: UserResponseToTrainerMapper):UserRepository{
        return UserRepositoryImpl(userResponseToTrainerMapper)
    }

    @Singleton
    @Provides
    fun provideUseCaseForLoginOActivity(userRepository: UserRepository,coroutineDispatcher: CoroutineDispatcher):LoginUserUseCase{
        return LoginUserUseCaseImpl(userRepository,coroutineDispatcher)
    }

    // REGISTER ACTIVITY PROVIDES
    @Singleton
    @Provides
    fun provideUserRegisterRepository(userResponseToTrainerMapper: UserResponseToTrainerMapper):UserRegisterRepository{
        return UserRegisterRepositoryImpl(userResponseToTrainerMapper)
    }

    @Singleton
    @Provides
    fun provideRegisterUserUseCase(userRegisterRepository: UserRegisterRepository,coroutineDispatcher: CoroutineDispatcher):RegisterUserUseCase{
        return RegisterUserUseCaseImpl(userRegisterRepository,coroutineDispatcher)
    }

    @Singleton
    @Provides
    fun provideRegisterViewModelFactory(useCase:RegisterUserUseCase):RegisterViewModelFactory{
        return RegisterViewModelFactory(useCase)
    }

    // ENTER TEAM PROVIDES
    @Singleton
    @Provides
    fun provideTeamResponseToTeamMapper():TeamResponseToTeamMapper{
        return TeamResponseToTeamMapper()
    }

    @Singleton
    @Provides
    fun provideTeamRepository(teamResponseToTeamMapper: TeamResponseToTeamMapper):TeamRepository{
        return TeamRepositoryImpl(teamResponseToTeamMapper)
    }

    @Singleton
    @Provides
    fun providePostTeamUseCase(teamRepository: TeamRepository,coroutineDispatcher: CoroutineDispatcher):PostTeamUseCase{
        return PostTeamUseCaseImpl(teamRepository,coroutineDispatcher)
    }

    @Singleton
    @Provides
    fun provideEnterTeamViewModelFactory(useCase:PostTeamUseCase):EnterTeamViewModelFactory{
        return EnterTeamViewModelFactory(useCase)
    }

    // CATEGORIES PROVIDES
    @Singleton
    @Provides
    fun provideCategoryResponseToCategoryMapper():CategoryResponseToCategoryMapper{
        return CategoryResponseToCategoryMapper()
    }

    @Singleton
    @Provides
    fun provideCategoryRepository(categoryResponseToCategoryMapper: CategoryResponseToCategoryMapper):CategoryRepository{
        return CategoryRepositoryImpl(categoryResponseToCategoryMapper)
    }

    @Singleton
    @Provides
    fun provideGetCategoriesUseCase(categoryRepository: CategoryRepository, coroutineDispatcher: CoroutineDispatcher):GetCategoriesUseCase{
        return GetCategoriesUseCaseImpl(categoryRepository,coroutineDispatcher)
    }
}