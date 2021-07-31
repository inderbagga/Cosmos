package com.inderbagga.cosmos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inderbagga.cosmos.data.repo.Repo

class InfoViewModelFactory constructor(private val repository: Repo)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InfoViewModel(repository) as T
    }
}