package com.example.app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.app.data_class.EntityPlayers

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(entityPlayers: EntityPlayers)

    @Update
    suspend fun updateUser(entityPlayers: EntityPlayers)

    @Delete
    suspend fun deleteUser(entityPlayers: EntityPlayers)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getUsers(): LiveData<List<EntityPlayers>>


    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getUsersWithoutLiveData(): List<EntityPlayers>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllPlayers(playersList: List<EntityPlayers>)

    @Transaction
    suspend fun replaceAllPlayers(playersList: List<EntityPlayers>) {
        deleteAllUsers()
        addAllPlayers(playersList)
    }
}