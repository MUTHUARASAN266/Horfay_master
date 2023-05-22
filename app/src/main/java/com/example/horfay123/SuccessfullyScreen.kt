package com.example.horfay123

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.horfay123.databinding.ActivitySuccessfullyScreenBinding

class SuccessfullyScreen : AppCompatActivity() {
    lateinit var binding: ActivitySuccessfullyScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySuccessfullyScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}