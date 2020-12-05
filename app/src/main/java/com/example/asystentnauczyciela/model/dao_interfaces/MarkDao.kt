package com.example.asystentnauczyciela.model.dao_interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.asystentnauczyciela.model.Mark
import com.example.asystentnauczyciela.model.StudentMarks
import java.util.*

@Dao
interface MarkDao {

    @Insert
    suspend fun addMark(mark: Mark)

    @Delete
    suspend fun deleteMark(mark: Mark)

    @Query("SELECT * FROM mark_table WHERE student_id = :studentId and group_id = :groupId")
    fun getStudentMarksFromGroup(studentId: Int, groupId: Int): LiveData<List<Mark>>

    @Query("SELECT * FROM student_table JOIN mark_table on mark_table.student_id = student_table.studentId WHERE date > :begin AND date < :end")
    fun getStudentsMarksBetween(begin: Date, end: Date): LiveData<List<StudentMarks>>
}