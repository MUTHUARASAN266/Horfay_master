package com.example.horfay123.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.horfay123.Dashboard
import com.example.horfay123.R
import com.example.horfay123.databinding.ActivitySigninScreenBinding

class SigninScreen : AppCompatActivity() {
    var TAG= SigninScreen::class.java.simpleName
    lateinit var binding: ActivitySigninScreenBinding
    private val CAMERA_PERMISSION_CODE = 100
    lateinit var textWatcher: TextWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        binding=ActivitySigninScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

            textviewForgotPassword?.setOnClickListener {
                startActivity(Intent(this@SigninScreen, ForgotPassword::class.java))
            }
            signinButtton.setOnClickListener {
                startActivity(Intent(this@SigninScreen,Dashboard::class.java))
            }

       }
    }

    private fun textChanger() {
        textWatcher=object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.editTextPhone?.text!!.isEmpty()){
                    binding.signinButtton.backgroundTintList = ContextCompat.getColorStateList(this@SigninScreen,R.color.button_back)
                    binding.signinButtton.setTextColor(resources.getColor(R.color.black))
                }else if (binding.editeTextPassword?.text!!.isEmpty()){
                    binding.signinButtton.backgroundTintList = ContextCompat.getColorStateList(this@SigninScreen,R.color.button_back)
                    binding.signinButtton.setTextColor(resources.getColor(R.color.black))
                }else if (binding.editTextPhone?.text!!.isNotEmpty()){
                    binding.signinButtton.backgroundTintList = ContextCompat.getColorStateList(this@SigninScreen,R.color.black)
                    binding.signinButtton.setTextColor(resources.getColor(R.color.white))
                }else if (binding.editeTextPassword?.text!!.isNotEmpty()){
                    binding.signinButtton.backgroundTintList = ContextCompat.getColorStateList(this@SigninScreen,R.color.black)
                    binding.signinButtton.setTextColor(resources.getColor(R.color.white))
                }
            }

            override fun afterTextChanged(string: Editable?) {
            }

        }

    }


    private fun buttonColor() {
        binding.apply {
            if (editTextPhone?.text!!.isEmpty() && editeTextPassword?.text!!.isEmpty()){
                binding.signinButtton.backgroundTintList = ContextCompat.getColorStateList(this@SigninScreen,R.color.white)
                binding.signinButtton.setTextColor(resources.getColor(R.color.black))
            }else{
                signinButtton.backgroundTintList = ContextCompat.getColorStateList(this@SigninScreen,R.color.black)
                signinButtton.setTextColor(resources.getColor(R.color.white))
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
        if (ContextCompat.checkSelfPermission(this@SigninScreen, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(this@SigninScreen, arrayOf(permission), requestCode)
        } else {
            Toast.makeText(this@SigninScreen, "Permission already granted", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@SigninScreen, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@SigninScreen, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}