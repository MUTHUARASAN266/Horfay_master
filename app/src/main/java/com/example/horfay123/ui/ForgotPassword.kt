package com.example.horfay123.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import com.example.horfay123.R
import com.example.horfay123.databinding.ActivityForgotPasswordBinding
import com.example.horfay123.snack

class ForgotPassword : AppCompatActivity() {
    lateinit var binding: ActivityForgotPasswordBinding
    lateinit var textWatcher: TextWatcher
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            backButton.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
            textChanger()
            resetPhone.addTextChangedListener(textWatcher)

            buttonResetPassword.setOnClickListener {
                if (resetPhone.text?.length!=10){
                    it.snack("enter mobile number",1500)
                }else{
                    startActivity(Intent(this@ForgotPassword, VerifyOTP::class.java))
                }
            }
        }

    }
    fun textChanger(){
        textWatcher = object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                buttonColorChange()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        }
    }

    fun buttonColorChange(){
        if (binding.resetPhone.text!!.isEmpty()){
            binding.buttonResetPassword.backgroundTintList=ContextCompat.getColorStateList(this@ForgotPassword,
                R.color.button_back
            )
            binding.buttonResetPassword.setTextColor(resources.getColor(R.color.black))
        }else{
            binding.buttonResetPassword.backgroundTintList=ContextCompat.getColorStateList(this@ForgotPassword,
                R.color.black
            )
            binding.buttonResetPassword.setTextColor(resources.getColor(R.color.white))
        }
    }

    override fun onResume() {
        super.onResume()
    }
}