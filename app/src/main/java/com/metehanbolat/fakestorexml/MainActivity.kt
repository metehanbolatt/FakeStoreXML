package com.metehanbolat.fakestorexml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.metehanbolat.fakestorexml.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.mainButton.setOnClickListener {
            viewModel.getAllProducts()
        }

        viewModel.mainUIState.observe(this) {
            when(it) {
                is MainUIState.Loading -> println("Loading")
                is MainUIState.Error -> println("Error: ${resources.getString(it.message)}")
                is MainUIState.Success -> println("Success: ${it.data}")
            }
        }

    }
}