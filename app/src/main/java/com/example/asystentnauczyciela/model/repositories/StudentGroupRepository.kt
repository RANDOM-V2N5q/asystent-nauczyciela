package com.example.asystentnauczyciela.model.repositories

import com.example.asystentnauczyciela.model.StudentGroup
import com.example.asystentnauczyciela.model.StudentGroupDao

class StudentGroupRepository(private val studentGroupDao: StudentGroupDao) {

    suspend fun add(studentGroup: StudentGroup) {
        studentGroupDao.addStudentGroup(studentGroup)
    }

    suspend fun delete(studentGroup: StudentGroup) {
        studentGroupDao.deleteStudentGroup(studentGroup)
    }

    suspend fun getStudentsInGroup(groupId: Int) {
        studentGroupDao.getStudentsInGroup(groupId)
    }

    suspend fun getStudentGroups(studentId: Int) {
        studentGroupDao.getStudentGroups(studentId)
    }
}