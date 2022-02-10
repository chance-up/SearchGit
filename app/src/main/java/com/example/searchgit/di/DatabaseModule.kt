package com.example.searchgit.di

import android.content.Context
import com.example.searchgit.data.AppDatabase
import com.example.searchgit.data.GitUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideAlbumDao(appDatabase: AppDatabase): GitUserDao = appDatabase.gitUserDao()
}