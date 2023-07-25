package com.example.horfay123.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.example.horfay123.R
import com.example.horfay123.databinding.ActivityEditProfileScreenBinding

class EditProfileScreen : AppCompatActivity() {
    lateinit var binding: ActivityEditProfileScreenBinding
    lateinit var textWatcher: TextWatcher
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        textChanger()

        binding.apply {
            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            prEdFrName.addTextChangedListener(textWatcher)
            prEdLaname.addTextChangedListener(textWatcher)
            prEdPhoneNumber.addTextChangedListener(textWatcher)
            prEdCountry.addTextChangedListener(textWatcher)
            prEdEmail.addTextChangedListener(textWatcher)
            prEdCity.addTextChangedListener(textWatcher)
        }
    }

    private fun textChanger() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textColorChanger()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        }
    }

    private fun textColorChanger() {
        binding.apply {
            if (prEdFrName.text?.isEmpty() == true) {
                editBtn.backgroundTintList =
                    ContextCompat.getColorStateList(this@EditProfileScreen, R.color.white)
                editBtn.setTextColor(ContextCompat.getColor(this@EditProfileScreen, R.color.black))
            } else if (prEdLaname.text?.length == 0) {
                editBtn.backgroundTintList =
                    ContextCompat.getColorStateList(this@EditProfileScreen, R.color.white)
                editBtn.setTextColor(ContextCompat.getColor(this@EditProfileScreen, R.color.black))
            } else if (prEdEmail.text?.length == 0) {
                editBtn.backgroundTintList =
                    ContextCompat.getColorStateList(this@EditProfileScreen, R.color.white)
                editBtn.setTextColor(ContextCompat.getColor(this@EditProfileScreen, R.color.black))
            } else if (prEdPhoneNumber.text?.length == 0) {
                editBtn.backgroundTintList =
                    ContextCompat.getColorStateList(this@EditProfileScreen, R.color.white)
                editBtn.setTextColor(ContextCompat.getColor(this@EditProfileScreen, R.color.black))
            } else if (prEdCountry.text?.length == 0) {
                editBtn.backgroundTintList =
                    ContextCompat.getColorStateList(this@EditProfileScreen, R.color.white)
                editBtn.setTextColor(ContextCompat.getColor(this@EditProfileScreen, R.color.black))
            } else if (prEdCity.text?.length == 0) {
                editBtn.backgroundTintList =
                    ContextCompat.getColorStateList(this@EditProfileScreen, R.color.white)
                editBtn.setTextColor(ContextCompat.getColor(this@EditProfileScreen, R.color.black))
            } else {
                editBtn.backgroundTintList =
                    ContextCompat.getColorStateList(this@EditProfileScreen, R.color.black)
                editBtn.setTextColor(ContextCompat.getColor(this@EditProfileScreen, R.color.white))
            }
        }


    }
}