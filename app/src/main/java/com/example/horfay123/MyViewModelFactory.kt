package com.example.horfay123

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.horfay123.repository.LoginRepository
import com.example.horfay123.repository.SignUpRepository
import com.example.horfay123.viewmodel.LoginViewModel
import com.example.horfay123.viewmodel.SignUpViewModel

class MyViewModelFactory constructor(private val repository: LoginRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
class MyViewModelFactory1 constructor(private val repository: SignUpRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            SignUpViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}