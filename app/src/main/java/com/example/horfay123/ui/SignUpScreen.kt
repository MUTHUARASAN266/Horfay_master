package com.example.horfay123.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.horfay123.R
import com.example.horfay123.adapter.DropDownAdapter
import com.example.horfay123.databinding.ActivitySignUpScreenBinding


class SignUpScreen : AppCompatActivity() {
    lateinit var binding: ActivitySignUpScreenBinding
    lateinit var textWatcher: TextWatcher
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //spinner
        countrySpiiner()

        binding.apply {
            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            textviewAlreadyAccount.setOnClickListener {
                startActivity(Intent(this@SignUpScreen, SigninScreen::class.java))
            }

        }

    }

    private fun countrySpiiner() {
        val country = resources.getStringArray(R.array.counry)
        val adapter = DropDownAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            country
        )
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                view: View?,
                int: Int,
                long: Long
            ) {
                if (parentView?.getPositionForView(view) == 0)
                    (parentView.getChildAt(0) as TextView).setTextColor(Color.GRAY)
                else
                    (parentView?.getChildAt(0) as TextView).setTextColor(Color.BLACK)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }

        }
    }

    override fun onResume() {
        super.onResume()
        textChanger()
                binding.editTextName.addTextChangedListener(textWatcher)
                binding.editTextLastname.addTextChangedListener(textWatcher)
                binding.editTextEmail.addTextChangedListener(textWatcher)
                binding.mobileNumber.addTextChangedListener(textWatcher)
                binding.edittextCity.addTextChangedListener(textWatcher)
                binding.editTextPassword.addTextChangedListener(textWatcher)
                binding.editTextConfirmPasswrd.addTextChangedListener(textWatcher)
    }

    private fun textChanger() {
        textWatcher=object : TextWatcher{

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.editTextName.text!!.isEmpty()){
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(this@SignUpScreen,
                        R.color.button_back
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.black))
                }
                else if (binding.editTextEmail.text!!.isEmpty()){
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(this@SignUpScreen,
                        R.color.button_back
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.black))
                }else if (binding.mobileNumber.text!!.isEmpty()){
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(this@SignUpScreen,
                        R.color.button_back
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.black))
                }else if (binding.edittextCity.text!!.isEmpty()){
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(this@SignUpScreen,
                        R.color.button_back
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.black))
                }else if (binding.editTextPassword.text!!.isEmpty()){
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(this@SignUpScreen,
                        R.color.button_back
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.black))
                }else if (binding.editTextConfirmPasswrd.text!!.isEmpty()){
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(this@SignUpScreen,
                        R.color.button_back
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.black))
                }

                else if (binding.editTextName.text!!.isNotEmpty()){
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(this@SignUpScreen,
                        R.color.black
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.white))
                }
                else if (binding.editTextEmail.text!!.isNotEmpty()){
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(this@SignUpScreen,
                        R.color.black
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.white))
                }else if (binding.mobileNumber.text!!.isNotEmpty()){
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(this@SignUpScreen,
                        R.color.black
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.white))
                }else if (binding.edittextCity.text!!.isNotEmpty()){
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(this@SignUpScreen,
                        R.color.black
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.white))
                }else if (binding.editTextPassword.text!!.isNotEmpty()){
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(this@SignUpScreen,
                        R.color.black
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.white))
                }else if (binding.editTextConfirmPasswrd.text!!.isNotEmpty()){
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(this@SignUpScreen,
                        R.color.black
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.white))
                }


                /*
                else if (binding.editTextEmail.text!!.isEmpty()){

                }else if (binding.mobileNumber.text!!.isEmpty()){

                }else if (binding.edittextCity.text!!.isEmpty()){

                }else if (binding.editTextPassword.text!!.isEmpty()){

                }else if (binding.editTextConfirmPasswrd.text!!.isEmpty()){

                }
                */
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        }
    }

}