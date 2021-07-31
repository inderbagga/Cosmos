package com.inderbagga.cosmos.data.repo

import com.inderbagga.cosmos.data.model.Info

interface Repo {

    suspend fun getInfo() : Info?
}