package com.example.app.data_class

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class PlayersData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
) : Parcelable
