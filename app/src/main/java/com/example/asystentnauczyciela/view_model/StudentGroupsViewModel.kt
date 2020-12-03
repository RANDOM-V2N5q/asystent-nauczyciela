package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.AppDatabase
import com.example.asystentnauczyciela.model.Group
import com.example.asystentnauczyciela.model.repositories.StudentGroupRepository

class StudentGroupsViewModel(application: Application): AndroidViewModel(application) {

    lateinit var studentGroups: LiveData<List<Group>>
    private var studentGroupRepository: StudentGroupRepository

    init {
        val studentGroupDao = AppDatabase.getDatabase(application).studentGroupDao()
        studentGroupRepository = StudentGroupRepository(studentGroupDao)
    }

    fun studentGroups(studentId: Int) {
        studentGroups = studentGroupRepository.getStudentGroups(studentId)
    }
}