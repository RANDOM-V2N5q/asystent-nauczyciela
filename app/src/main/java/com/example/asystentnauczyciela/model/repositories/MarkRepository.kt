package com.example.asystentnauczyciela.model.repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.Mark
import com.example.asystentnauczyciela.model.StudentMarks
import com.example.asystentnauczyciela.model.dao_interfaces.MarkDao
import java.util.*

class MarkRepository(private val markDao: MarkDao) {

    suspend fun add(mark: Mark) {
        markDao.addMark(mark)
    }

    suspend fun delete(mark: Mark) {
        markDao.deleteMark(mark)
    }

    fun getStudentMarksFromGroup(studentId: Int, groupId: Int): LiveData<List<Mark>> {
        return markDao.getStudentMarksFromGroup(studentId, groupId)
    }

    fun getStudentsMarksBetween(begin: Date, end: Date): LiveData<List<StudentMarks>> {
        return markDao.getStudentsMarksBetween(begin, end)
    }
}