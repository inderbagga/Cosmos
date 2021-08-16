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
import androidx.lifecycle.lifecycleScope
import com.inderbagga.cosmos.utils.Network
import kotlinx.coroutines.launch

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

                if(Network.isConnected(this)){
                    lifecycleScope.launch {

                        val bitmap= viewModel.fetchImage(it.url)
                        binding.imageView.setImageBitmap(bitmap)
                    }
                }else viewModel.status.postValue(1)

                binding.info=it
                binding.progressBar.visibility= View.GONE
            }
        })

        viewModel.status.observe(this,{
            it?.let{

                supportActionBar?.apply {

                    when(it){
                        1 -> {
                            binding.progressBar.visibility= View.GONE
                            this.subtitle=resources.getString(R.string.internet_not_available)
                        }
                        4 -> {
                            binding.progressBar.visibility= View.GONE
                            binding.description.text=resources.getString(R.string.empty_cache)
                        }
                    }
                }
            }
        })

        viewModel.getInfo()

        binding.lifecycleOwner=this
    }
}