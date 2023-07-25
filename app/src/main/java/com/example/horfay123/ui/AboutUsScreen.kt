package com.example.horfay123.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.horfay123.R
import com.example.horfay123.databinding.ActivityAboutUsScreenBinding

class AboutUsScreen : AppCompatActivity() {
    lateinit var binding: ActivityAboutUsScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAboutUsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

        }
    }
}