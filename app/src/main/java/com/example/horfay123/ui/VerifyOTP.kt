package com.example.horfay123.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import com.example.horfay123.R
import com.example.horfay123.databinding.ActivityVerifyOtpBinding
import com.example.horfay123.snack
import com.example.horfay123.toast

class VerifyOTP : AppCompatActivity() {
    lateinit var binding: ActivityVerifyOtpBinding
    lateinit var textWatcher: TextWatcher
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textChange()
        binding.firstPinView.addTextChangedListener(textWatcher)
        binding.apply {
            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            val timer=object :CountDownTimer(60000,1000){
                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    timerOtp.text="Re-send code in 0:${millisUntilFinished/1000}"
                }

                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    timerOtp.text = "Done"
                }

            }
            timer.start()
            buttonContinue.setOnClickListener {
                if (firstPinView.text!!.isEmpty()){
                    it.snack("OTP Is Empty",1500)
                }else{
                    startActivity(Intent(this@VerifyOTP,CreateNewPassword::class.java))
                }
            }
        }

    }

   fun textChange(){
        textWatcher = object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.firstPinView.text!!.isEmpty()){
                    binding.buttonContinue.backgroundTintList=ContextCompat.getColorStateList(this@VerifyOTP, R.color.button_back)
                    binding.buttonContinue.setTextColor(resources.getColor(R.color.black))
                }else{
                    binding.buttonContinue.backgroundTintList=ContextCompat.getColorStateList(this@VerifyOTP, R.color.black)
                    binding.buttonContinue.setTextColor(resources.getColor(R.color.white))
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        }
    }
}
