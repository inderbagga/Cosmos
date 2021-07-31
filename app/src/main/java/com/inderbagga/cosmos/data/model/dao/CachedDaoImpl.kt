package com.inderbagga.cosmos.data.model.dao

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.inderbagga.cosmos.data.dao.CachedDao
import com.inderbagga.cosmos.data.model.Info
import com.inderbagga.cosmos.utils.CacheManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CachedDaoImpl @Inject constructor(private val gson:Gson, private val cacheManager: CacheManager): CachedDao {

    override suspend fun getCachedInfo() : Info? {

        return cacheManager.getStoredInfo().run {

            if(this.isEmpty()) return null

            gson.fromJson(this, object : TypeToken<Info>() {}.type)
        }
    }

    override suspend fun saveInCache(info:Info) {

        val json: String = gson.toJson(info)
        cacheManager.setStoredInfo(json)
    }
}