package com.example.horfay123.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.horfay123.R
import com.example.horfay123.databinding.ActivityServicePageScreenBinding

class ServicePageScreen : AppCompatActivity() {
    lateinit var binding: ActivityServicePageScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityServicePageScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}