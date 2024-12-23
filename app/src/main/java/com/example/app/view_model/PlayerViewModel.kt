package com.example.app.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.app.data.PlayerDatabase
import com.example.app.data_class.EntityPlayers
import com.example.app.repository.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PlayerViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PlayerRepository

    init {
        val playerDao = PlayerDatabase.getDatabase(application).playerDao()
        repository = PlayerRepository(playerDao)
    }

    fun getUsers(): LiveData<List<EntityPlayers>> {
        return repository.getUsers()
    }

    fun addUser(entityPlayers: EntityPlayers) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(entityPlayers)
        }
    }

    suspend fun getUsersWithoutLiveData(): List<EntityPlayers> {
        return withContext(Dispatchers.IO) {
            repository.getUsersWithoutLiveData()
        }
    }

    fun addAllPlayers(playersList: List<EntityPlayers>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.replaceAllPlayers(playersList)
        }
    }

}
