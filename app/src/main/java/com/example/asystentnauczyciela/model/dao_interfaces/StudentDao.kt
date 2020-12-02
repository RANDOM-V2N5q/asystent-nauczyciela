package com.example.asystentnauczyciela.model.dao_interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.asystentnauczyciela.model.Student

@Dao
interface StudentDao {

    @Insert
    suspend fun addStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT * FROM student_table")
    fun allStudents(): LiveData<List<Student>>
}