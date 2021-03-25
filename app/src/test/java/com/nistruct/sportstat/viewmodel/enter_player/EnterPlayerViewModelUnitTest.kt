package com.nistruct.sportstat.viewmodel.enter_player

import com.nistruct.sportstat.ui.enter_player.EnterPlayerViewModel
import com.nistruct.sportstat.usecase.PostPlayerUseCase
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EnterPlayerViewModelUnitTest{
    private lateinit var sut: EnterPlayerViewModel

    @Mock
    private lateinit var postPlayeruseCase:PostPlayerUseCase

    @Before
    fun setUp() {
        sut = EnterPlayerViewModel(postPlayeruseCase)
    }

    @Test
    fun `empty player name returns false`(){
        val result = sut.validatePlayerNameEntry("")

        assertThat(result,equalTo(false))
    }
}