package com.example.asystentnauczyciela.model.dao_interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.asystentnauczyciela.model.Group

@Dao
interface GroupDao {

    @Insert
    suspend fun addGroup(group: Group)

    @Delete
    suspend fun deleteGroup(group: Group)

    @Query("SELECT * FROM group_table")
    fun allGroups(): LiveData<List<Group>>
}