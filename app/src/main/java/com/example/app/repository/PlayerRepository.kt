package com.example.app.repository

import androidx.lifecycle.LiveData
import com.example.app.data.PlayerDao
import com.example.app.data_class.EntityPlayers

class PlayerRepository(private val playerDao: PlayerDao) {

    fun getUsers(): LiveData<List<EntityPlayers>> {
        return playerDao.getUsers()
    }

    suspend fun getUsersWithoutLiveData(): List<EntityPlayers> {
        return playerDao.getUsersWithoutLiveData()
    }

    suspend fun addUser(entityPlayers: EntityPlayers) {
        playerDao.addUser(entityPlayers)
    }

    suspend fun replaceAllPlayers(playersList: List<EntityPlayers>) {
        playerDao.replaceAllPlayers(playersList)
    }

}


