package com.example.horfay123.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.horfay123.R
import com.example.horfay123.databinding.ActivityTcnScreenBinding

class TCnScreen : AppCompatActivity() {
    lateinit var binding: ActivityTcnScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTcnScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}