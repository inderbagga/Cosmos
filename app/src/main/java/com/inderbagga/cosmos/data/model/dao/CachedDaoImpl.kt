package com.inderbagga.cosmos.data.model.dao

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.inderbagga.oneinall.data.model.Info
import com.inderbagga.oneinall.utils.CacheManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CachedDaoImpl @Inject constructor(val gson:Gson, val cacheManager: CacheManager): CachedDao {

    override suspend fun getCachedInfo() : Info? {

        return cacheManager.getStoredInfo()?.run {

            if(this.isNullOrEmpty()) return null

            gson.fromJson(this, object : TypeToken<Info>() {}.type)
        }
    }

    override suspend fun saveInCache(info:Info) {

        val json: String = gson.toJson(info)
        cacheManager.setStoredInfo(json)
    }
}