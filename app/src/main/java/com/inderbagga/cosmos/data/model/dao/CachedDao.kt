package com.inderbagga.cosmos.data.dao

import com.inderbagga.cosmos.data.model.Info

interface CachedDao {

    suspend fun getCachedInfo(): Info?

    suspend fun saveInCache(info:Info)
}