package com.example.horfay123.ui

import LoginResult
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.horfay123.*
import com.example.horfay123.databinding.ActivitySigninScreenBinding
import com.example.horfay123.network.NetworkService
import com.example.horfay123.repository.LoginRepository
import com.example.horfay123.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SigninScreen : AppCompatActivity() {
    var TAG = SigninScreen::class.java.simpleName
    lateinit var binding: ActivitySigninScreenBinding
    private val CAMERA_PERMISSION_CODE = 100
    lateinit var textWatcher: TextWatcher
    lateinit var loginViewModel: LoginViewModel
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        binding = ActivitySigninScreenBinding.inflate(layoutInflater)
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

        val retrofitService = NetworkService.create()
        val mainRepository = LoginRepository(retrofitService)
        loginViewModel =
            ViewModelProvider(this, MyViewModelFactory(mainRepository))[LoginViewModel::class.java]

        binding.apply {

            textviewCreateNew?.setOnClickListener {
                startActivity(Intent(this@SigninScreen, SignUpScreen::class.java))

                /*
              checkPermission(
                  Manifest.permission.CAMERA,
                  CAMERA_PERMISSION_CODE)

//               val dialog = Dialog(this@SigninScreen, R.style.Theme_Black_NoTitleBar_Fullscreen)
//               dialog.setContentView(R.layout.activity_signin_screen)
//               dialog.show()
              val builder = AlertDialog.Builder(this@SigninScreen)
              //set title for alert dialog
              builder.setTitle("sample dialog")
              //set message for alert dialog
              builder.setMessage("hello")
              builder.setIcon(android.R.drawable.ic_dialog_alert)
              val alertDialog: AlertDialog = builder.create()
              // Set other dialog properties
              alertDialog.setCancelable(true)
              alertDialog.show()*/

            }

            /* signinButtton.setOnClickListener {
           if (phoneNumberText.text.isEmpty()){

           }else if (editeTextPassword?.text?.isEmpty() == true){

           }else{
               signinButtton.setTextColor(resources.getColor(R.color.white))
               signinButtton.backgroundTintList =baseContext.resources.getColorStateList(R.color.black)

           }
       }*/
            textview1.setOnClickListener {
                startActivity(Intent(this@SigninScreen, HostActivity::class.java))
            }

            textviewForgotPassword?.setOnClickListener {
                startActivity(Intent(this@SigninScreen, ForgotPassword::class.java))
            }
            signinButtton.setOnClickListener {
                val countryCode = countryCodePicker?.selectedCountryCodeWithPlus
                if (editTextPhone?.text!!.isEmpty()) {
                    it.snack("email and password empty", 2000)
                    Log.e(TAG, "email empty")
                } else if (editeTextPassword?.text!!.isEmpty()) {
                    it.snack("email and password empty", 2000)
                    Log.e(TAG, "password empty")
                } else {
                    Log.e(TAG, "countryCode: ${countryCode + editTextPhone.text.toString()}")
                    CoroutineScope(Dispatchers.Main).launch {
                        loginViewModel.userLogin(
                            countryCode + editTextPhone.text.toString().trim(),
                            editeTextPassword.text.toString().trim(),
                            "User",
                            "d3tza_BTQeCEbis8q023Pr:APA91bGzbT8XaMOMLVH2ozX8sFV-uvLLxjQIHBl-TMq5NITmyi02GHcNQdiGHN93ePztpaRWiIbUjHuAcNY7r41Fj9Uq09hsXYJFNYV4El_kA9jmnZczsamfDB_dUUo7fjfCv86YmEj0"
                        )
                        loginViewModel.loginResult.observe(
                            this@SigninScreen,
                            Observer { loginResult ->
                                when (loginResult) {
                                    is LoginResult.Success -> {
                                        // Log.e(TAG, "onCreate: login")
                                        //Log.e(TAG, "onCreate: login-->${loginResult.token?.message}")
                                        //  Log.e(TAG, "onCreate: login-->${loginResult.token?.details?.username}")
                                        Log.e(
                                            TAG,
                                            "onCreate: login-->${loginResult.token?.details?.username}"
                                        )
                                        val sharedPreferences = SharedPreferences(this@SigninScreen)
                                        sharedPreferences.saveData(
                                            "u_firstName",
                                            loginResult.token?.details?.firstName as String
                                        )
                                        sharedPreferences.saveData(
                                            "u_lastName",
                                            loginResult.token.details.lastName as String
                                        )
                                        sharedPreferences.saveData(
                                            "u_email",
                                            loginResult.token.details.email as String
                                        )
                                        sharedPreferences.saveData(
                                            "u_phoneNumber",
                                            loginResult.token.details.phoneNumber as String
                                        )
                                        sharedPreferences.saveData(
                                            "u_country",
                                            loginResult.token.details.country as String
                                        )
                                        sharedPreferences.saveData(
                                            "u_city",
                                            loginResult.token.details.city as String
                                        )
                                        sharedPreferences.saveData(
                                            "u_user_name",
                                            loginResult.token.details.username as String
                                        )
                                        it.snack(loginResult.token.status, 2000)
                                        startActivity(
                                            Intent(
                                                this@SigninScreen,
                                                Dashboard::class.java
                                            )
                                        )

                                        loginViewModel.complete()
                                        // myViewModel.jobStop()
                                    }
                                    is LoginResult.Error -> {
                                        Log.e(
                                            TAG,
                                            "onCreate: login Failed-->${loginResult.message}"
                                        )
                                        Log.e(TAG, "onCreate: login Failed")

                                        it.snack(loginResult.message, 2000)
                                        loginViewModel.complete()
                                        // myViewModel.jobStop()
                                    }
                                    else -> {
                                        Log.e(TAG, "onCreate: main Error")
                                        it.snack(" main Error", 2000)
                                    }
                                }


                            })
                        loginViewModel.loading1.observe(this@SigninScreen, Observer { isLoading ->

                            if (isLoading) {
                                progressCircular?.show()
                            } else {
                                progressCircular?.hide()
                            }
                        })
                    }

                }
                //startActivity(Intent(this@SigninScreen,Dashboard::class.java))
            }
        }
    }

    private fun textChanger() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.editTextPhone?.text!!.isEmpty()) {
                    binding.signinButtton.backgroundTintList =
                        ContextCompat.getColorStateList(this@SigninScreen, R.color.button_back)
                    binding.signinButtton.setTextColor(
                        ContextCompat.getColor(
                            this@SigninScreen,
                            R.color.black
                        )
                    )
                } else if (binding.editeTextPassword?.text!!.isEmpty()) {
                    binding.signinButtton.backgroundTintList =
                        ContextCompat.getColorStateList(this@SigninScreen, R.color.button_back)
                    binding.signinButtton.setTextColor(
                        ContextCompat.getColor(
                            this@SigninScreen,
                            R.color.black
                        )
                    )
                } else if (binding.editTextPhone?.text!!.isNotEmpty()) {
                    binding.signinButtton.backgroundTintList =
                        ContextCompat.getColorStateList(this@SigninScreen, R.color.black)
                    binding.signinButtton.setTextColor(
                        ContextCompat.getColor(
                            this@SigninScreen,
                            R.color.white
                        )
                    )
                } else if (binding.editeTextPassword?.text!!.isNotEmpty()) {
                    binding.signinButtton.backgroundTintList =
                        ContextCompat.getColorStateList(this@SigninScreen, R.color.black)
                    binding.signinButtton.setTextColor(
                        ContextCompat.getColor(
                            this@SigninScreen,
                            R.color.white
                        )
                    )
                }
            }

            @SuppressLint("SetTextI18n")
            override fun afterTextChanged(string: Editable?) {
                val inputlength: Int = binding.editTextPhone!!.text.toString().length
                if (count <= inputlength && inputlength == 5) {

                    binding.editTextPhone!!.setText(binding.editTextPhone!!.text.toString() + " ")

                    val pos: Int = binding.editTextPhone!!.text!!.length
                    binding.editTextPhone!!.setSelection(pos)

                } else if (count >= inputlength && (inputlength == 5)) {
                    binding.editTextPhone!!.setText(
                        binding.editTextPhone!!.text.toString()
                            .substring(0, binding.editTextPhone!!.text.toString().length - 1)
                    )

                    val pos: Int = binding.editTextPhone!!.text!!.length
                    binding.editTextPhone!!.setSelection(pos);
                }
                count = binding.editTextPhone!!.text.toString().length
            }

        }

    }


    private fun buttonColor() {
        binding.apply {
            if (editTextPhone?.text!!.isEmpty() && editeTextPassword?.text!!.isEmpty()) {
                binding.signinButtton.backgroundTintList =
                    ContextCompat.getColorStateList(this@SigninScreen, R.color.white)
                binding.signinButtton.setTextColor(
                    ContextCompat.getColor(
                        this@SigninScreen,
                        R.color.black
                    )
                )
            } else {
                signinButtton.backgroundTintList =
                    ContextCompat.getColorStateList(this@SigninScreen, R.color.black)
                signinButtton.setTextColor(ContextCompat.getColor(this@SigninScreen, R.color.white))
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onResume() {
        super.onResume()
        // button background color changing function

        textChanger()
        binding.editTextPhone?.addTextChangedListener(textWatcher)
        binding.editeTextPassword?.addTextChangedListener(textWatcher)

        Log.d(TAG, "onResume: ")
    }

    private fun apiCall() {
        binding.apply {
            signinButtton.setOnClickListener {
                if (editTextPhone?.text!!.isEmpty()) {
                    it.snack("email and password empty", 2000)
                    Log.e(TAG, "email empty")
                } else if (editeTextPassword?.text!!.isEmpty()) {
                    it.snack("email and password empty", 2000)
                    Log.e(TAG, "password empty")
                } else {
                    CoroutineScope(Dispatchers.Main).launch {
                        loginViewModel.userLogin(
                            editTextPhone.text.toString().trim(),
                            editeTextPassword.text.toString().trim(),
                            "User",
                            "d3tza_BTQeCEbis8q023Pr:APA91bGzbT8XaMOMLVH2ozX8sFV-uvLLxjQIHBl-TMq5NITmyi02GHcNQdiGHN93ePztpaRWiIbUjHuAcNY7r41Fj9Uq09hsXYJFNYV4El_kA9jmnZczsamfDB_dUUo7fjfCv86YmEj0"
                        )
                        loginViewModel.loginResult.observe(
                            this@SigninScreen,
                            Observer { loginResult ->
                                when (loginResult) {
                                    is LoginResult.Success -> {
                                        // Log.e(TAG, "onCreate: login")
                                        //Log.e(TAG, "onCreate: login-->${loginResult.token?.message}")
                                        Log.e(
                                            TAG,
                                            "onCreateR: login-->${loginResult.token?.details?.username}"
                                        )
                                        it.snack("${loginResult.token?.message}", 2000)
                                        startActivity(
                                            Intent(
                                                this@SigninScreen,
                                                Dashboard::class.java
                                            )
                                        )
                                        loginViewModel.complete()
                                        // myViewModel.jobStop()
                                    }
                                    is LoginResult.Error -> {
                                        Log.e(
                                            TAG,
                                            "onCreate: login Failed-->${loginResult.message}"
                                        )
                                        Log.e(TAG, "onCreate: login Failed")
                                        it.snack("login Failed", 2000)
                                        loginViewModel.complete()
                                        // myViewModel.jobStop()
                                    }
                                    else -> {
                                        Log.e(TAG, "onCreate: main Error")
                                        it.snack(" main Error", 2000)
                                    }
                                }


                            })
                    }

                }

                //startActivity(Intent(this@SigninScreen,Dashboard::class.java))
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")

    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@SigninScreen,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {

            // Requesting the permission
            ActivityCompat.requestPermissions(this@SigninScreen, arrayOf(permission), requestCode)
        } else {
            Toast.makeText(this@SigninScreen, "Permission already granted", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@SigninScreen, "Camera Permission Granted", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this@SigninScreen, "Camera Permission Denied", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}