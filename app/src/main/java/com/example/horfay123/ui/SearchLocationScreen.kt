package com.example.horfay123.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.horfay123.R
import com.example.horfay123.databinding.ActivitySearchLocationScreenBinding

class SearchLocationScreen : AppCompatActivity() {
    lateinit var binding: ActivitySearchLocationScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySearchLocationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}