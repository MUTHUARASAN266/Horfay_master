package com.example.horfay123.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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