package com.example.asystentnauczyciela.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group_table")
data class Group(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String
)