package com.example.horfay123.ui

import SignInResult
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.widget.AdapterView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.horfay123.MyViewModelFactory1
import com.example.horfay123.R
import com.example.horfay123.adapter.DropDownAdapter
import com.example.horfay123.databinding.ActivitySignUpScreenBinding
import com.example.horfay123.model.SignUpData
import com.example.horfay123.network.NetworkService
import com.example.horfay123.repository.SignUpRepository
import com.example.horfay123.snack
import com.example.horfay123.viewmodel.SignUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SignUpScreen : AppCompatActivity() {
    lateinit var binding: ActivitySignUpScreenBinding
    lateinit var textWatcher: TextWatcher
    lateinit var viewModel: SignUpViewModel
    private val signUpViewModel: SignUpViewModel by viewModels()
    private var citry = ""
    var count = 0
    val TAG = "SignUpScreen"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnApplyWindowInsetsListener { _, windowInsets ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val imeHeight = windowInsets.getInsets(WindowInsets.Type.ime()).bottom
                binding.root.setPadding(0, 0, 0, imeHeight)
                val insets = windowInsets.getInsets(WindowInsets.Type.ime() or WindowInsets.Type.systemGestures())
                insets
            }

            windowInsets
        }


        //spinner
        countrySpiiner()
        val retrofitService = NetworkService.create()
        val mainRepository = SignUpRepository(retrofitService)
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory1(mainRepository)
        )[SignUpViewModel::class.java]

        binding.apply {
            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            backButton.setOnClickListener {

            }
            textviewAlreadyAccount.setOnClickListener {
                startActivity(Intent(this@SignUpScreen, SigninScreen::class.java))
            }
            buttonSingnup.setOnClickListener {

                //validation()
                //  mobileNumber.setText(String.format("% d", 1234567890));
                binding.apply {
                    when {
                        editTextName.text.toString().isEmpty() -> {
                            it.snack("name is empty", 1500)

                        }
                        editTextLastname.text.toString().isEmpty() -> {
                            it.snack("lastname is empty", 1500)

                        }
                        editTextEmail.text.toString().isEmpty() -> {
                            it.snack("email is empty", 1500)

                        }
                        mobileNumber.text.toString().isEmpty() -> {
                            it.snack("mobile number is empty", 1500)

                        }
                        edittextCity.text.toString().isEmpty() -> {
                            it.snack("city is empty", 1500)

                        }
                        editTextPassword.text.toString().isEmpty() -> {
                            it.snack("password is empty", 1500)

                        }
                        editTextConfirmPasswrd.text.toString().isEmpty() -> {
                            it.snack("confirm password is empty", 1500)

                        }
                        editTextPassword.text.toString().trim()
                                != editTextConfirmPasswrd.text.toString().trim() -> {
                            it.snack("password miss match", 1500)
                            Log.e(TAG, "validation: -> is different")
                        }
                        else -> {
                            val countryCode = codePicker.selectedCountryCodeWithPlus
                            Log.e(
                                TAG,
                                "countryCode: ${countryCode + binding.mobileNumber.text.toString()}"
                            )
                            CoroutineScope(Dispatchers.Main).launch {
                                viewModel.signUp(
                                    SignUpData
                                        (
                                        "User",
                                        binding.edittextCity.text.toString(),
                                        citry,
                                        binding.editTextEmail.text.toString(),
                                        binding.editTextName.text.toString(),
                                        binding.editTextLastname.text.toString(),
                                        binding.editTextPassword.text.toString(),
                                        countryCode + binding.mobileNumber.text.toString(),
                                        binding.editTextLastname.text.toString(),
                                    )
                                )

                                viewModel.signInResult.observe(this@SignUpScreen) { result ->
                                    when (result) {
                                        is SignInResult.Success -> {
                                            val token = result.response.message
                                            val userId = result.response.status
                                            Log.e(TAG, "onCreate: $token")
                                            Log.e(TAG, "onCreate: $userId")
                                            it.snack(userId, 1500)
                                            // Handle successful sign-in
                                        }
                                        is SignInResult.Error -> {
                                            val errorMessage = result.message
                                            Log.e(TAG, "onCreate: $errorMessage")
                                            it.snack(errorMessage, 1500)
                                            // Handle sign-in error
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


//                "password": "User@123",
//                "username": "mohamed",
//                "phone_number":"+9110000 88888",
//                "city":"kasmir",
//                "email":"uio00p0@yopmail.com",
//                "last_name":"mohhmed",
//                "first_name":"raiyan",
//                "country":"india",
//                "action":"User"

            }

        }

    }

    private fun validation() {
        binding.apply {
            if (editTextName.text.toString().isEmpty()) {

            } else if (editTextLastname.text.toString().isEmpty()) {

            } else if (editTextEmail.text.toString().isEmpty()) {

            } else if (mobileNumber.text.toString().isEmpty()) {

            } else if (edittextCity.text.toString().isEmpty()) {

            } else if (editTextPassword.text.toString().isEmpty()) {

            } else if (editTextConfirmPasswrd.text.toString().isEmpty()) {

            } else {

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
                position: Int,
                id: Long

            ) {
                citry = country[position]
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
        textWatcher = object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.editTextName.text!!.isEmpty()) {
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(
                        this@SignUpScreen,
                        R.color.button_back
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.black))
                } else if (binding.editTextEmail.text!!.isEmpty()) {
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(
                        this@SignUpScreen,
                        R.color.button_back
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.black))
                } else if (binding.mobileNumber.text!!.isEmpty()) {
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(
                        this@SignUpScreen,
                        R.color.button_back
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.black))
                } else if (binding.edittextCity.text!!.isEmpty()) {
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(
                        this@SignUpScreen,
                        R.color.button_back
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.black))
                } else if (binding.editTextPassword.text!!.isEmpty()) {
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(
                        this@SignUpScreen,
                        R.color.button_back
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.black))
                } else if (binding.editTextConfirmPasswrd.text!!.isEmpty()) {
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(
                        this@SignUpScreen,
                        R.color.button_back
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.black))
                } else if (binding.editTextName.text!!.isNotEmpty()) {
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(
                        this@SignUpScreen,
                        R.color.black
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.white))
                } else if (binding.editTextEmail.text!!.isNotEmpty()) {
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(
                        this@SignUpScreen,
                        R.color.black
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.white))
                } else if (binding.mobileNumber.text!!.isNotEmpty()) {
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(
                        this@SignUpScreen,
                        R.color.black
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.white))
                } else if (binding.edittextCity.text!!.isNotEmpty()) {
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(
                        this@SignUpScreen,
                        R.color.black
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.white))
                } else if (binding.editTextPassword.text!!.isNotEmpty()) {
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(
                        this@SignUpScreen,
                        R.color.black
                    )
                    binding.buttonSingnup.setTextColor(resources.getColor(R.color.white))
                } else if (binding.editTextConfirmPasswrd.text!!.isNotEmpty()) {
                    binding.buttonSingnup.backgroundTintList = ContextCompat.getColorStateList(
                        this@SignUpScreen,
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

                //add space after every 5 characters
                val inputlength: Int = binding.mobileNumber.text.toString().length
                if (count <= inputlength && inputlength == 5) {

                    binding.mobileNumber.setText(binding.mobileNumber.text.toString() + " ")

                    val pos: Int = binding.mobileNumber.text!!.length
                    binding.mobileNumber.setSelection(pos)

                } else if (count >= inputlength && (inputlength == 5)) {
                    binding.mobileNumber.setText(
                        binding.mobileNumber.text.toString()
                            .substring(0, binding.mobileNumber.text.toString().length - 1)
                    )

                    val pos: Int = binding.mobileNumber.text!!.length
                    binding.mobileNumber.setSelection(pos);
                }
                count = binding.mobileNumber.text.toString().length
            }

        }
    }

}