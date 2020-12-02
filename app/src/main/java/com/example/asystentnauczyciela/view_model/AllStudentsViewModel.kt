package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.AppDatabase
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.repositories.StudentRepository
import kotlinx.coroutines.launch

class AllStudentsViewModel(application: Application): AndroidViewModel(application) {

    val allStudent: LiveData<List<Student>>
    private val studentRepository: StudentRepository

    init {
        val studentDao = AppDatabase.getDatabase(application).studentDao()
        studentRepository = StudentRepository(studentDao)
        allStudent = studentRepository.allStudents
    }

    fun addStudent(student: Student) {
        viewModelScope.launch {
            studentRepository.add(student)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            studentRepository.delete(student)
        }
    }
}