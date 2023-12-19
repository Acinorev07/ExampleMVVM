package com.example.examplemvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.examplemvvm.databinding.ActivityMainBinding
import com.example.examplemvvm.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //viewBinding
    private lateinit var binding: ActivityMainBinding
    // Declaramos el viewModel
    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.onCreate()

        //Aqui accedemos al viewModel que se conecta al modelo
        quoteViewModel.quoteModel.observe(this,
            Observer {
            if (it != null) {
                binding.tvQuotes.text = it.quote
            }
                if (it != null) {
                    binding.tvAutor.text = it.author
                }
        })

        quoteViewModel.isLoading.observe(this, Observer{
            binding.progress.isVisible = it
        })

        //Ahora hacemos que toda la vista sea un boton
        binding.viewContainer.setOnClickListener{
            quoteViewModel.randomQuote()
        }
    }
}