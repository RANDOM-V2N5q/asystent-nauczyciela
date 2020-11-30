package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.AppDatabase
import com.example.asystentnauczyciela.model.repositories.MarkRepository
import kotlinx.coroutines.launch

class MarkViewModel(application: Application): AndroidViewModel(application) {

    private val markRepository: MarkRepository

    init {
        val markDao = AppDatabase.getDatabase(application).markDao()
        markRepository = MarkRepository(markDao)
    }
    
    fun getStudentMarksFromGroup(studentId: Int, groupId: Int) {
        viewModelScope.launch {
            markRepository.getStudentMarksFromGroup(studentId, groupId)
        }
    }
}