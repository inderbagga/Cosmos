package com.inderbagga.cosmos.di

import android.content.Context
import com.google.gson.Gson
import com.inderbagga.cosmos.data.dao.CachedDao
import com.inderbagga.cosmos.data.dao.RemoteDao
import com.inderbagga.cosmos.data.dao.RemoteDaoImpl
import com.inderbagga.cosmos.data.model.dao.CachedDaoImpl
import com.inderbagga.cosmos.data.repo.Repo
import com.inderbagga.cosmos.repo.RepoImpl
import com.inderbagga.cosmos.ui.viewmodel.InfoViewModelFactory
import com.inderbagga.cosmos.utils.CacheManager
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
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideCacheManager(@ApplicationContext context: Context): CacheManager= CacheManager(context)

    @Singleton
    @Provides
    fun provideCachedDao(gson: Gson, cachedManager: CacheManager): CachedDao = CachedDaoImpl(gson,cachedManager)

    @Singleton
    @Provides
    fun provideRemoteDao(): RemoteDao = RemoteDaoImpl()

    @Singleton
    @Provides
    fun provideRepo(@ApplicationContext context: Context, remoteDao: RemoteDao, cachedDao: CachedDao,): Repo = RepoImpl(context,cachedDao,remoteDao)

    @Singleton
    @Provides
    fun provideViewModelFactory(repo: Repo)= InfoViewModelFactory(repo)
}