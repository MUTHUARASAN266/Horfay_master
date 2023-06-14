package com.example.horfay123.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import com.example.horfay123.R
import com.example.horfay123.databinding.ActivityPinScreenBinding
import com.example.horfay123.snack

class PinScreen : AppCompatActivity() {
    lateinit var binding: ActivityPinScreenBinding
    lateinit var textWatcher: TextWatcher
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPinScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textChanger()
        binding.passPin.addTextChangedListener(textWatcher)
        binding.apply {
            buttonContinuePin.setOnClickListener {
                if (passPin.text!!.isEmpty()) {
                    it.snack("Pin Is Empty", 1500)
                } else {
                    startActivity(Intent(this@PinScreen, Dashboard::class.java))
                }
            }
            forgoPinText.setOnClickListener {
                startActivity(Intent(this@PinScreen, ForgotPassword::class.java))
            }
        }
    }

    private fun textChanger() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.passPin.text!!.isEmpty()) {
                    binding.passPin.backgroundTintList =
                        ContextCompat.getColorStateList(this@PinScreen, R.color.button_back)
                    binding.buttonContinuePin.setTextColor(resources.getColor(R.color.black))
                } else {
                    binding.buttonContinuePin.backgroundTintList =
                        ContextCompat.getColorStateList(this@PinScreen, R.color.black)
                    binding.buttonContinuePin.setTextColor(resources.getColor(R.color.white))
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        }
    }
}
