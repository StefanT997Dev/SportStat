package com.nistruct.sportstat.viewmodel.players

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nistruct.sportstat.data.models.ui_models.Player
import com.nistruct.sportstat.ui.players.PlayersViewModel
import com.nistruct.sportstat.usecase.GetPlayersUseCase
import com.nistruct.sportstat.usecase.result.DataResult
import com.nistruct.sportstat.util.TestCoroutineRule
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PlayersViewModelUnitTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var getPlayersUseCase: GetPlayersUseCase

    private lateinit var sut: PlayersViewModel

    @Before
    fun setUp() {
        sut = PlayersViewModel(getPlayersUseCase)
    }

    @Test
    fun `getPlayers should post loading result when called`() = testCoroutineRule.runBlockingTest {
        // since flow and test code executes on the same thread we cannot check live data values until flow finishes
        // and since flow will assign two values (Loading + Success/Error) we have to save the first one somehow
        var firstValueOfPlayersLiveData: DataResult<List<Player>>? = null

        val observer = Observer<DataResult<List<Player>>> { value ->
            if (firstValueOfPlayersLiveData == null) firstValueOfPlayersLiveData = value
        }
        sut.playersLiveData.observeForever(observer)

        // When
        `when`(getPlayersUseCase.execute()).thenReturn(flow { emit(DataResult.Success(listOf<Player>()) )})

        sut.getPlayers()

        assertTrue(firstValueOfPlayersLiveData == DataResult.Loading)
    }

    @Test
    fun `getPlayers should post successful result when use case returns a successful result`() = testCoroutineRule.runBlockingTest {
        // since flow and test code executes on the same thread we cannot check live data values until flow finishes
        // so, we want to keep a list of all live data values and check them when flow finishes
        val playersLiveDataValues = mutableListOf<DataResult<List<Player>>>()
        val observer = Observer<DataResult<List<Player>>> { value -> playersLiveDataValues.add(value) }
        sut.playersLiveData.observeForever { value -> playersLiveDataValues.add(value) }

        // Given
        val playerList = listOf(
            Player(0, "", "", ""),
            Player(0, "", "", "")
        )

        val successfulDataResult = DataResult.Success(playerList)
        val flowWithSuccessfulResult = flow<DataResult<List<Player>>> {
            emit(successfulDataResult)
        }

        // When
        `when`(getPlayersUseCase.execute()).thenReturn(flowWithSuccessfulResult)

        sut.getPlayers()

        // Then
        assertTrue(playersLiveDataValues[1] == successfulDataResult)

        sut.playersLiveData.removeObserver(observer)
    }

    @Test
    fun `getPlayers should post error when use case returns a error result`() = testCoroutineRule.runBlockingTest {
        // since flow and test code executes on the same thread we cannot check live data values until flow finishes
        // so, we want to keep a list of all live data values and check them when flow finishes
        val playersLiveDataValues = mutableListOf<DataResult<List<Player>>>()
        val observer = Observer<DataResult<List<Player>>> { value -> playersLiveDataValues.add(value) }
        sut.playersLiveData.observeForever { value -> playersLiveDataValues.add(value) }

        // Given

        val errorResult = DataResult.Error(NullPointerException(""))
        val flowWithErrorResult = flow<DataResult<List<Player>>> {
            emit(errorResult)
        }

        // When
        `when`(getPlayersUseCase.execute()).thenReturn(flowWithErrorResult)

        sut.getPlayers()

        // Then
        assertTrue(playersLiveDataValues[1] == errorResult)

        sut.playersLiveData.removeObserver(observer)
    }
}