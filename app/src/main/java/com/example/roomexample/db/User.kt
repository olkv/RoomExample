package com.example.roomexample.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "firts_name")
    val first_name: String?,
    @ColumnInfo(name = "last_name")
    val last_name: String?
)