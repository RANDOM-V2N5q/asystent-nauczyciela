package com.example.asystentnauczyciela.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentGroupDao {

    @Insert
    suspend fun addStudentGroup(studentGroup: StudentGroup)

    @Delete
    suspend fun deleteStudentGroup(studentGroup: StudentGroup)

    @Query("SELECT * FROM student_group_table join student_table on student_group_table.student_id = student_table.id WHERE group_id = :groupId")
    suspend fun getStudentsInGroup(groupId: Int)

    @Query("SELECT * FROM student_group_table join group_table on student_group_table.group_id = group_table.id WHERE student_id = :studentId")
    suspend fun  getStudentGroups(studentId: Int)
}