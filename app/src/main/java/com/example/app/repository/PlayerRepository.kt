package com.example.app.repository

import androidx.lifecycle.LiveData
import com.example.app.data.PlayerDao
import com.example.app.data_class.PlayersData

class PlayerRepository(private val playerDao: PlayerDao) {

    val readAllData: LiveData<List<PlayersData>> = playerDao.readAllData()

    suspend fun addUser(playersData: PlayersData) {
        playerDao.addUser(playersData)
    }

    suspend fun updateUser(playersData: PlayersData) {
        playerDao.updateUser(playersData)
    }


    suspend fun deleteUser(playersData: PlayersData) {
        playerDao.deleteUser(playersData)
    }

    suspend fun savePLayersName(playersName: PlayersData) {
        return playerDao.savePLayersName(playersName)
    }

}


