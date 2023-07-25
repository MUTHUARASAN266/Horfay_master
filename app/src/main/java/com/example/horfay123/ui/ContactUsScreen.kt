package com.example.horfay123.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import com.example.horfay123.R
import com.example.horfay123.databinding.ActivityContactUsScreenBinding

class ContactUsScreen : AppCompatActivity() {
    lateinit var binding: ActivityContactUsScreenBinding
    lateinit var textWatcher: TextWatcher
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityContactUsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textChanger()
        binding.apply {
            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            contactNameEdit.addTextChangedListener(textWatcher)
            contactEmailEdit.addTextChangedListener(textWatcher)
            contactPhoneEdit.addTextChangedListener(textWatcher)
            contactDescriptionEdit.addTextChangedListener(textWatcher)
        }
    }

    private fun textChanger() {
        textWatcher= object : TextWatcher {
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
            if (contactNameEdit.text?.length==0){
                conSubmitBtn.backgroundTintList=ContextCompat.getColorStateList(this@ContactUsScreen,R.color.white)
                conSubmitBtn.setTextColor(ContextCompat.getColor(this@ContactUsScreen,R.color.black))
            }else if (contactEmailEdit.text?.length==0){
                conSubmitBtn.backgroundTintList=ContextCompat.getColorStateList(this@ContactUsScreen,R.color.white)
                conSubmitBtn.setTextColor(ContextCompat.getColor(this@ContactUsScreen,R.color.black))
            }else if (contactPhoneEdit.text?.length==0){
                conSubmitBtn.backgroundTintList=ContextCompat.getColorStateList(this@ContactUsScreen,R.color.white)
                conSubmitBtn.setTextColor(ContextCompat.getColor(this@ContactUsScreen,R.color.black))
            }else if (contactDescriptionEdit.text?.length==0){
                conSubmitBtn.backgroundTintList=ContextCompat.getColorStateList(this@ContactUsScreen,R.color.white)
                conSubmitBtn.setTextColor(ContextCompat.getColor(this@ContactUsScreen,R.color.black))
            }
            else{
                conSubmitBtn.backgroundTintList=ContextCompat.getColorStateList(this@ContactUsScreen,R.color.black)
                conSubmitBtn.setTextColor(ContextCompat.getColor(this@ContactUsScreen,R.color.white))
            }
        }
    }
}