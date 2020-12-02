package com.example.asystentnauczyciela.model.dao_interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.StudentGroup

@Dao
interface StudentGroupDao {

    @Insert
    suspend fun addStudentGroup(studentGroup: StudentGroup)

    @Delete
    suspend fun deleteStudentGroup(studentGroup: StudentGroup)

    @Query("SELECT * FROM student_group_table join student_table on student_group_table.student_id = student_table.id WHERE group_id = :groupId")
    fun getStudentsInGroup(groupId: Int): LiveData<List<Student>>

    @Query("SELECT * FROM student_group_table join group_table on student_group_table.group_id = group_table.id WHERE student_id = :studentId")
    fun  getStudentGroups(studentId: Int): LiveData<List<StudentGroup>>

    @Query("DELETE FROM student_group_table WHERE student_id = :studentId AND group_id = :groupId")
    suspend fun deleteStudentGroupQ(studentId: Int, groupId: Int)
}