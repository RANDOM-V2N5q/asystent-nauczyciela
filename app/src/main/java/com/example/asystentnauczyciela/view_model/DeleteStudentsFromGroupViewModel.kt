package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.AppDatabase
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.StudentGroup
import com.example.asystentnauczyciela.model.repositories.StudentGroupRepository
import kotlinx.coroutines.launch

class DeleteStudentsFromGroupViewModel(application: Application): AndroidViewModel(application) {

    lateinit var studentsInGroup: LiveData<List<Student>>
    private val selectedItem: MutableSet<Int>
    private val studentGroupRepository: StudentGroupRepository

    init {
        val studentGroupDao = AppDatabase.getDatabase(application).studentGroupDao()
        studentGroupRepository = StudentGroupRepository(studentGroupDao)
        selectedItem = mutableSetOf()
    }

    //TODO: coś z tym ↓
    fun studentsInGroup(groupId: Int) {
        studentsInGroup = studentGroupRepository.getStudentsInGroup(groupId)
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

    fun deleteSelectedStudents(groupId: Int) {
        selectedItem.forEach {
            viewModelScope.launch {
                studentGroupRepository.delete(it, groupId)
            }
        }
    }
}