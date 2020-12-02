package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.AppDatabase
import com.example.asystentnauczyciela.model.Group
import com.example.asystentnauczyciela.model.repositories.GroupRepository
import kotlinx.coroutines.launch

class AllGroupsViewModel(application: Application): AndroidViewModel(application) {

    val allGroups: LiveData<List<Group>>
    private val groupRepository: GroupRepository

    init {
        val groupDao = AppDatabase.getDatabase(application).groupDao()
        groupRepository = GroupRepository(groupDao)
        allGroups = groupDao.allGroups()
    }

    fun addGroup(group: Group) {
        viewModelScope.launch {
            groupRepository.add(group)
        }
    }

    fun deleteGroup(group: Group) {
        viewModelScope.launch {
            groupRepository.delete(group)
        }
    }
}