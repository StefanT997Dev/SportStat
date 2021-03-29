package com.nistruct.sportstat.viewmodel.enter_player

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nistruct.sportstat.data.models.ui_models.Player
import com.nistruct.sportstat.extension.getOrAwaitValue
import com.nistruct.sportstat.ui.enter_player.EnterPlayerViewModel
import com.nistruct.sportstat.usecase.PostPlayerUseCase
import com.nistruct.sportstat.usecase.result.DataResult
import com.nistruct.sportstat.util.TestCoroutineRule
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.flow
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyObject
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EnterPlayerViewModelUnitTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var sut: EnterPlayerViewModel

    @Mock
    private lateinit var postPlayeruseCase: PostPlayerUseCase

    @Before
    fun setUp() {
        sut = EnterPlayerViewModel(postPlayeruseCase)

        sut.playerName = "Miki"
        sut.playerEmail = "fanste@gmail.com"
        sut.playerImage = "asdsdasas"
        sut.playerPosition = "front"
        sut.playerPhoneNumber = "054/2333-323"
    }

    @Test
    fun `empty player name returns false`() {
        val result = sut.validatePlayerNameEntry("")

        assertThat(result, equalTo(false))
    }

    @Test
    fun `testing whether enterPlayer function collects non null data`() {
        sut.enterPlayer()

        val value = sut.playerLiveData.getOrAwaitValue()

        assertTrue(value is DataResult<Player>)
    }

    @Test
    fun `testing whether enterPlayer function returns a Player response`() =

        testCoroutineRule.runBlockingTest {
            var firstValueOfPlayersLiveData: DataResult<Player>? = null

            val observer = Observer<DataResult<Player>> { value ->
                if (firstValueOfPlayersLiveData == null) firstValueOfPlayersLiveData = value
            }
            sut.playerLiveData.observeForever(observer)

            `when`(
                postPlayeruseCase.execute(
                    anyObject()
                )
            )
                .thenReturn(flow { emit(DataResult.Success(Player(1, "sdsds", "Miki", "front"))) })

            sut.enterPlayer()

            val value = sut.playerLiveData.getOrAwaitValue()

            assertTrue(value is DataResult<Player>)
        }
}