package com.example.app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.app.data_class.PlayersData

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(playersData: PlayersData)

    @Update
    suspend fun updateUser(playersData: PlayersData)

    @Delete
    suspend fun deleteUser(playersData: PlayersData)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<PlayersData>>

}