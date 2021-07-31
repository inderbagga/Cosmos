package com.inderbagga.cosmos.repo

import android.content.Context
import com.inderbagga.cosmos.data.dao.RemoteDao
import com.inderbagga.cosmos.data.model.Info
import com.inderbagga.cosmos.data.repo.Repo
import com.inderbagga.cosmos.remote.API
import com.inderbagga.cosmos.utils.Network


class RepoImpl constructor(private val context:Context, private val remoteDao: RemoteDao) : Repo{

    override suspend fun getInfo(): Info? = if(Network.isConnected(context)){
        remoteDao.getRemoteInfo(API.KEY)
    } else null
}