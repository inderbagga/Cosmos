package com.inderbagga.cosmos.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inderbagga.cosmos.data.model.Info
import com.inderbagga.cosmos.data.repo.Repo
import kotlinx.coroutines.launch

class InfoViewModel( private val repo: Repo) : ViewModel() {

    val info: MutableLiveData<Info> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val status: MutableLiveData<Int> = MutableLiveData()

    fun getInfo(){
        viewModelScope.launch {
            isLoading.postValue(true)

            val response=repo.getInfo()

            if(response==null){
                status.postValue(4)
            }
            info?.postValue(response)

        }
    }

}