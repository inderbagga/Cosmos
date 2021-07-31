package com.inderbagga.cosmos.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inderbagga.cosmos.data.model.Info
import com.inderbagga.cosmos.data.repo.Repo

class InfoViewModel( private val repo: Repo) : ViewModel() {

    val info: MutableLiveData<Info> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val status: MutableLiveData<Int> = MutableLiveData()

    fun getInfo(){

    }

}