package com.example.asystentnauczyciela.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "mark_table",
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["id"],
            childColumns = ["student_id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Group::class,
            parentColumns = ["id"],
            childColumns = ["group_id"],
            onDelete = CASCADE
        )
    ]
)
data class Mark(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var student_id: Int,
    var group_id: Int,
    var value: Int,
    var date: Date
)