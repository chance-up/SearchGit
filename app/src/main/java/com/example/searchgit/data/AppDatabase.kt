package com.example.searchgit.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GitUser::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun gitUserDao(): GitUserDao

    companion object {
        @Volatile
        private var INSTANCE:AppDatabase?=null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(AppDatabase::class) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "gitUserDB").build().also { INSTANCE = it }
            }
        }
    }
}