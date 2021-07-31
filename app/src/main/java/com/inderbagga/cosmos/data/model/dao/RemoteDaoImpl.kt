package com.inderbagga.cosmos.data.dao

import com.inderbagga.cosmos.data.model.Info
import com.inderbagga.cosmos.remote.ApiClient

class RemoteDaoImpl constructor(): RemoteDao {

     override suspend fun getRemoteInfo(key: String): Info? =
          ApiClient.service.getRemoteInfo(key)
}