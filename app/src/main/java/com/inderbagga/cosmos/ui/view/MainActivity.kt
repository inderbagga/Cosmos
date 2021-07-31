package com.inderbagga.cosmos.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.inderbagga.cosmos.R
import com.inderbagga.cosmos.databinding.ActivityMainBinding
import com.inderbagga.cosmos.ui.viewmodel.InfoViewModel
import com.inderbagga.cosmos.ui.viewmodel.InfoViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject private lateinit var viewModelFactory: InfoViewModelFactory

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: InfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel= ViewModelProvider(this,viewModelFactory).get(InfoViewModel::class.java)


        binding.lifecycleOwner=this
    }
}