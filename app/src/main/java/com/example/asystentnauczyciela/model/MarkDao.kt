package com.example.asystentnauczyciela.model

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

interface MarkDao {

    @Insert
    suspend fun addMark(mark: Mark)

    @Delete
    suspend fun deleteMark(mark: Mark)

    @Query("SELECT * FROM mark_table WHERE student_id = :studentId and group_id = :groupId")
    suspend fun getStudentMarksFromGroup(studentId: Int, groupId: Int): LiveData<List<Mark>>
}