package com.inderbagga.cosmos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inderbagga.cosmos.data.repo.Repo
import javax.inject.Inject

class InfoViewModelFactory @Inject constructor(private val repository: Repo)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InfoViewModel(repository) as T
    }
}