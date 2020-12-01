package com.example.asystentnauczyciela.model.repositories

import com.example.asystentnauczyciela.model.Mark
import com.example.asystentnauczyciela.model.MarkDao

class MarkRepository(private val markDao: MarkDao) {

    suspend fun add(mark: Mark) {
        markDao.addMark(mark)
    }

    suspend fun delete(mark: Mark) {
        markDao.deleteMark(mark)
    }

    fun getStudentMarksFromGroup(studentId: Int, groupId: Int) {
        markDao.getStudentMarksFromGroup(studentId, groupId)
    }
}