package com.inderbagga.cosmos.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

    @Inject lateinit var viewModelFactory: InfoViewModelFactory

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: InfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel= ViewModelProvider(this,viewModelFactory).get(InfoViewModel::class.java)

        viewModel.info.observe(this,  {

            it?.let {

                supportActionBar?.subtitle=it.title+" as on "+it.date
                viewModel.isLoading.postValue(false)


                binding.info=it
                binding.progressBar.visibility= View.GONE
            }
        })

        viewModel.getInfo()

        binding.lifecycleOwner=this
    }
}