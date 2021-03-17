package com.nistruct.sportstat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.repository.Repository

class PlayersRecyclerViewViewModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayersRecyclerViewViewModel(repository) as T
    }

}