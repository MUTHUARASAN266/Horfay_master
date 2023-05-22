package com.example.horfay123.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.horfay123.databinding.LanguageScreenBinding

class LanguageScreen : AppCompatActivity() {
    lateinit var binding: LanguageScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=LanguageScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonEnglish.setOnClickListener{
            startActivity(Intent(this, SigninScreen::class.java))
        }
    }
}