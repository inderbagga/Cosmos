package com.inderbagga.cosmos.ui.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inderbagga.cosmos.data.model.Info
import com.inderbagga.cosmos.data.repo.Repo
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.net.UnknownServiceException

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

    suspend fun fetchImage(url:String) : Bitmap {

        val deffered= viewModelScope.async(Dispatchers.IO){
            try {
                val url = URL(url)
                return@async BitmapFactory.decodeStream(url.openConnection().getInputStream())
            } catch (e: IOException){
                Timber.e("IOException: ${e.message}")
            } catch (e: UnknownServiceException){
                Timber.e("UnknownServiceException: ${e.message}")
            } catch (e: MalformedURLException){
                Timber.e("MalformedURLException: ${e.message}")
            }
        }

        return (deffered as Deferred<Bitmap>).await()
    }

}