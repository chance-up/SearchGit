package com.example.searchgit.di

import com.example.searchgit.api.GitUserApi
import com.example.searchgit.data.GitUserDao
import com.example.searchgit.repository.GitUserAPIRepository
import com.example.searchgit.repository.GitUserDBRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun bindGitUserApiRepo(api: GitUserApi): GitUserAPIRepository {
        return GitUserAPIRepository(api)
    }

    @Provides
    fun bindGitUserLocalRepo(dao: GitUserDao): GitUserDBRepository {
        return GitUserDBRepository(dao)
    }
}