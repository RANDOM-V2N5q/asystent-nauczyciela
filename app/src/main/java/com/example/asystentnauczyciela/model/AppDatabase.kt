package com.example.asystentnauczyciela.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class, Group::class, Mark::class, StudentGroup::class], version = 1, exportSchema = false)
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