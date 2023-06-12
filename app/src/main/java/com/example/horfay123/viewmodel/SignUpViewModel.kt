package com.example.horfay123.viewmodel

import LoginResult
import SignInResult
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horfay123.model.SignUpData
import com.example.horfay123.repository.SignUpRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SignUpViewModel(private val signUpRepository: SignUpRepository):ViewModel() {
    val TAG="SignUpViewModel"

    private val _signInResult = MutableLiveData<SignInResult>()
    val signInResult: LiveData<SignInResult> = _signInResult

    var job: Job? = null

    fun signUp(userData: SignUpData) {
        viewModelScope.launch {
            try {
                val response = signUpRepository.signUp(userData)
                Log.e(TAG, "signUp: ->userData= $userData")
                if (response != null) {
                    _signInResult.value = SignInResult.Success(response)
                } else {
                    _signInResult.value = SignInResult.Error("Sign-in failed")
                }
            } catch (e: Exception) {
                _signInResult.value = SignInResult.Error("Sign-in failed")
            }
        }
    }
    fun signUp1(password: String,username: String,phone: String,city: String,email: String,lastName: String,firstName: String,country: String,action: String) {
        viewModelScope.launch {
            try {
                val response = signUpRepository.signUp1(password, username,phone, city, email,lastName,firstName, country, action)
                if (response != null) {
                    _signInResult.value = SignInResult.Success(response)
                } else {
                    _signInResult.value = SignInResult.Error("Sign-in failed")
                }
            } catch (e: Exception) {
                _signInResult.value = SignInResult.Error("Sign-in failed")
            }
        }
    }
}
