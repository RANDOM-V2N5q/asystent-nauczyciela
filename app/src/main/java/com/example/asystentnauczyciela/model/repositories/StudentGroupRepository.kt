package com.example.asystentnauczyciela.model.repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.StudentGroup
import com.example.asystentnauczyciela.model.dao_interfaces.StudentGroupDao

class StudentGroupRepository(private val studentGroupDao: StudentGroupDao) {

    suspend fun add(studentGroup: StudentGroup) {
        studentGroupDao.addStudentGroup(studentGroup)
    }

    suspend fun delete(studentId: Int, groupId: Int) {
        studentGroupDao.deleteStudentGroupQ(studentId, groupId)
    }

    fun getStudentsInGroup(groupId: Int): LiveData<List<Student>> {
        return studentGroupDao.getStudentsInGroup(groupId)
    }

    fun getStudentGroups(studentId: Int) {
        studentGroupDao.getStudentGroups(studentId)
    }
}