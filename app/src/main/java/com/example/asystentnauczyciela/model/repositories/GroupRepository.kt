package com.example.asystentnauczyciela.model.repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.Group
import com.example.asystentnauczyciela.model.GroupDao

class GroupRepository(private val groupDao: GroupDao) {

    val allGroups: LiveData<List<Group>> = groupDao.allGroups()

    suspend fun add(group: Group) {
        groupDao.addGroup(group)
    }

    suspend fun delete(group: Group) {
        groupDao.deleteGroup(group)
    }
}