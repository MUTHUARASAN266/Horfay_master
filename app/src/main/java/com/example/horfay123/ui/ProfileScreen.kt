package com.example.horfay123.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.horfay123.R
import com.example.horfay123.SharedPreferences
import com.example.horfay123.databinding.ActivityProfileScreenBinding

class ProfileScreen : AppCompatActivity() {
    lateinit var binding: ActivityProfileScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProfileScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        binding.apply {

            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            val sharedPreferences=SharedPreferences(this@ProfileScreen)
           prEtFrName.text=sharedPreferences.loadData("u_firstName")
           prEtLaname.text=sharedPreferences.loadData("u_lastName")
           prEtEmail.text=sharedPreferences.loadData("u_email")
           prPhoneNumber.text=sharedPreferences.loadData("u_phoneNumber")
           prCountry.text=sharedPreferences.loadData("u_country")
           prCity.text=sharedPreferences.loadData("u_city")
            editBtn.setOnClickListener {
                startActivity(Intent(this@ProfileScreen,EditProfileScreen::class.java))
            }
        }
    }
}