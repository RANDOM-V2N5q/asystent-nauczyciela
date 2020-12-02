package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.AppDatabase
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.StudentGroup
import com.example.asystentnauczyciela.model.repositories.StudentGroupRepository
import com.example.asystentnauczyciela.model.repositories.StudentRepository
import kotlinx.coroutines.launch

class AddStudentToGroupViewModel(application: Application): AndroidViewModel(application)  {

    val allStudents: LiveData<List<Student>>
    private val selectedItem: MutableSet<Int>
    private val studentGroupRepository: StudentGroupRepository
    private val studentRepository: StudentRepository

    init {
        val studentGroupDao = AppDatabase.getDatabase(application).studentGroupDao()
        val studentDao = AppDatabase.getDatabase(application).studentDao()
        studentGroupRepository = StudentGroupRepository(studentGroupDao)
        studentRepository = StudentRepository((studentDao))
        allStudents = studentDao.allStudents()
        selectedItem = mutableSetOf()
    }

    fun select(id: Int) {
        selectedItem.add(id)
    }

    fun deselect(id: Int) {
        selectedItem.remove(id)
    }

    fun isSelected(id: Int): Boolean {
        //TODO: one line
        if(selectedItem.find { it == id } == null) {
            return false
        }
        return true
    }

    fun addSelectedStudents(groupId: Int) {
        selectedItem.forEach {
            val studentGroup = StudentGroup(0, it, groupId)
            viewModelScope.launch {
                studentGroupRepository.add(studentGroup)
            }
        }
    }
}