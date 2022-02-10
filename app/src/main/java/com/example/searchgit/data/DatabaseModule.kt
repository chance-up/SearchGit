package com.example.searchgit.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GitUser::class], version = 1, exportSchema = false)
abstract class DatabaseModule:RoomDatabase() {
    abstract fun gitUserDao(): GitUserDao

    companion object {
        @Volatile
        private var INSTANCE:DatabaseModule?=null

        fun getInstance(context: Context): DatabaseModule {
            return INSTANCE ?: synchronized(DatabaseModule::class) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    DatabaseModule::class.java, "gitUserDB").build().also { INSTANCE = it }
            }
        }
    }
}