package com.inderbagga.cosmos.repo

import android.content.Context
import com.inderbagga.cosmos.data.dao.CachedDao
import com.inderbagga.cosmos.data.dao.RemoteDao
import com.inderbagga.cosmos.data.model.Info
import com.inderbagga.cosmos.data.repo.Repo
import com.inderbagga.cosmos.remote.API
import com.inderbagga.cosmos.utils.Network
import javax.inject.Inject


class RepoImpl @Inject constructor(private val context:Context, private val cachedDao: CachedDao, private val remoteDao: RemoteDao) : Repo{

    override suspend fun getInfo(): Info? = if(Network.isConnected(context)){
        remoteDao.getRemoteInfo(API.KEY).also {
            it?.let {
                cachedDao.saveInCache(it)
            }
        }
    } else cachedDao.getCachedInfo()
}