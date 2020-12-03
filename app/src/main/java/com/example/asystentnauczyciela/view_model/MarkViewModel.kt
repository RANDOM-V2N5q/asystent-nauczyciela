package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.AppDatabase
import com.example.asystentnauczyciela.model.Mark
import com.example.asystentnauczyciela.model.repositories.MarkRepository
import kotlinx.coroutines.launch

class MarkViewModel(application: Application): AndroidViewModel(application) {

    lateinit var studentMarks: LiveData<List<Mark>>
    private val markRepository: MarkRepository

    init {
        val markDao = AppDatabase.getDatabase(application).markDao()
        markRepository = MarkRepository(markDao)
    }

    fun studentMarks(studentId: Int, groupId: Int) {
        studentMarks = markRepository.getStudentMarksFromGroup(studentId, groupId)
    }

    fun deleteMark(mark: Mark) {
        viewModelScope.launch {
            markRepository.delete(mark)
        }
    }

    fun addMark(mark: Mark) {
        viewModelScope.launch {
            markRepository.add(mark)
        }
    }
}