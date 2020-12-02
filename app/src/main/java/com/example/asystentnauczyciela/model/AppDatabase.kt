package com.example.asystentnauczyciela.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.asystentnauczyciela.model.dao_interfaces.GroupDao
import com.example.asystentnauczyciela.model.dao_interfaces.MarkDao
import com.example.asystentnauczyciela.model.dao_interfaces.StudentDao

@Database(entities = [Student::class, Group::class, Mark::class, StudentGroup::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun groupDao(): GroupDao
    abstract fun markDao(): MarkDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE

            if(tempInstance != null) {
                return tempInstance
            }
            else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }

}