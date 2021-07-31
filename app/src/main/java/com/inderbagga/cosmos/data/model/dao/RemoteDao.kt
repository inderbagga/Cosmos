package com.inderbagga.cosmos.data.dao

import com.inderbagga.cosmos.data.model.Info

interface RemoteDao {

     suspend fun getRemoteInfo(key:String): Info?
}