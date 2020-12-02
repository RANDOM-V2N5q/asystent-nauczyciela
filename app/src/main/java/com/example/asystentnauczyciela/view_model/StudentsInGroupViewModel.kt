package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.AppDatabase
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.repositories.StudentGroupRepository

class StudentsInGroupViewModel(application: Application): AndroidViewModel(application) {

    private val studentGroupRepository: StudentGroupRepository

    init {
        val studentGroupDao = AppDatabase.getDatabase(application).studentGroupDao()
        studentGroupRepository = StudentGroupRepository(studentGroupDao)
    }

    fun studentsInGroup(groupId: Int): LiveData<List<Student>> {
        return studentGroupRepository.getStudentsInGroup(groupId)
    }
}