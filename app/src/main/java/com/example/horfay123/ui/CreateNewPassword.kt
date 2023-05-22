package com.example.horfay123.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import com.example.horfay123.R
import com.example.horfay123.SuccessfullyScreen
import com.example.horfay123.databinding.ActivityCreateNewPasswordBinding
import com.example.horfay123.snack

class CreateNewPassword : AppCompatActivity() {
    lateinit var binding: ActivityCreateNewPasswordBinding
    lateinit var textWatcher: TextWatcher
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCreateNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            buttonSubmit.setOnClickListener {
                if (editTextNewPassword.text.toString().trim()==editTextConfirmPassword.text.toString().trim()){
                    startActivity(Intent(this@CreateNewPassword,SuccessfullyScreen::class.java))
                }else{
                    it.snack("password mismatch",1500)
                }
            }
        }
        textChanger()
        binding.editTextNewPassword.addTextChangedListener(textWatcher)
        binding.editTextConfirmPassword.addTextChangedListener(textWatcher)

    }

    private fun textChanger() {
        textWatcher=object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.editTextNewPassword.text!!.isEmpty()){
                    binding.buttonSubmit.backgroundTintList=ContextCompat.getColorStateList(this@CreateNewPassword, R.color.button_back)
                    binding.buttonSubmit.setTextColor(resources.getColor(R.color.black))
                }else if (binding.editTextConfirmPassword.text!!.isEmpty()){
                    binding.buttonSubmit.backgroundTintList=ContextCompat.getColorStateList(this@CreateNewPassword, R.color.button_back)
                    binding.buttonSubmit.setTextColor(resources.getColor(R.color.black))
                }else{
                    binding.buttonSubmit.backgroundTintList=ContextCompat.getColorStateList(this@CreateNewPassword, R.color.black)
                    binding.buttonSubmit.setTextColor(resources.getColor(R.color.white))
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        }
    }
}