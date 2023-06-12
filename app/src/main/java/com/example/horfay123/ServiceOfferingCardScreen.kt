package com.example.horfay123

import android.R
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.horfay123.databinding.ActivityServiceOfferingCardScreenBinding


class ServiceOfferingCardScreen : AppCompatActivity() {
    lateinit var binding: ActivityServiceOfferingCardScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityServiceOfferingCardScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()

        }
        binding.simpleRatingBar1.setOnRatingChangeListener { ratingBar, rating, fromUser ->
            binding.ratingText1.text=rating.toString()
            val rotation: Animation = AnimationUtils.loadAnimation(this, com.example.horfay123.R.anim.rotation)
          //  binding.simpleRatingBar1.startAnimation(rotation)
            //ratingBar.startAnimation(rotation)
            ratingBar.rotation
            ratingBar.animation
        }
        binding.simpleRatingBar.setOnRatingChangeListener { ratingBar, rating, fromUser ->
            binding.ratingText.text=rating.toString()
        }

    }
}