package com.example.app.data_class

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class EntityPlayers(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String = "",
    var gender: Int = 0,
) : Parcelable


