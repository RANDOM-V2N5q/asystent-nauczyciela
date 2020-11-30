package com.example.asystentnauczyciela.model.repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.StudentDao

class StudentRepository(private val studentDao: StudentDao) {

    val allStudents: LiveData<List<Student>> = studentDao.allStudents()

    suspend fun add(student: Student) {
        studentDao.addStudent(student)
    }

    suspend fun delete(student: Student) {
        studentDao.deleteStudent(student)
    }
}