package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.AppDatabase
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.repositories.StudentGroupRepository

class StudentsInGroupViewModel(application: Application): AndroidViewModel(application) {

    lateinit var studentsInGroup: LiveData<List<Student>>
    private val studentGroupRepository: StudentGroupRepository

    init {
        val studentGroupDao = AppDatabase.getDatabase(application).studentGroupDao()
        studentGroupRepository = StudentGroupRepository(studentGroupDao)
    }

    //TODO: XD
    fun studentsInGroup(groupId: Int) {
        studentsInGroup = studentGroupRepository.getStudentsInGroup(groupId)
    }
}