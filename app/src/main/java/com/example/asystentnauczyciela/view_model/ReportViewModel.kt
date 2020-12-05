package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.AppDatabase
import com.example.asystentnauczyciela.model.StudentMarks
import com.example.asystentnauczyciela.model.repositories.MarkRepository
import java.util.*

class ReportViewModel(application: Application): AndroidViewModel(application) {

    lateinit var marks: LiveData<List<StudentMarks>>
    private var markRepository: MarkRepository

    init {
        val markDao = AppDatabase.getDatabase(application).markDao()
        markRepository = MarkRepository(markDao)
    }

    fun Marks(begin: Date, end: Date) {
        marks = markRepository.getStudentsMarksBetween(begin, end)
    }
}