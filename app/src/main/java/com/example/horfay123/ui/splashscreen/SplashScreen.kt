package com.example.horfay123.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.horfay123.R
import com.example.horfay123.ui.LanguageScreen

class SplashScreen:AppCompatActivity() {
    private val handler=Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        val logoImage=findViewById<ImageView>(R.id.horfay_logo)
        val slide=AnimationUtils.loadAnimation(this,R.anim.side_slide)
        logoImage.animation=slide
        handler.postDelayed(Runnable {
            startActivity(Intent(this, LanguageScreen::class.java))
        },2000)
    }
}