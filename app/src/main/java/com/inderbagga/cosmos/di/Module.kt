package com.inderbagga.cosmos.di

import android.content.Context
import com.inderbagga.cosmos.data.dao.RemoteDao
import com.inderbagga.cosmos.data.dao.RemoteDaoImpl
import com.inderbagga.cosmos.data.repo.Repo
import com.inderbagga.cosmos.repo.RepoImpl
import com.inderbagga.cosmos.ui.viewmodel.InfoViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Module{

    @Singleton
    @Provides
    fun provideRemoteDao(): RemoteDao = RemoteDaoImpl()

    @Singleton
    @Provides
    fun provideRepo(@ApplicationContext context: Context, remoteDao: RemoteDao): Repo = RepoImpl(context,remoteDao)

    @Singleton
    @Provides
    fun provideViewModelFactory(repo: Repo)= InfoViewModelFactory(repo)
}