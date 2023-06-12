package com.example.horfay123.viewmodel

import LoginResult
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horfay123.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(val repository: LoginRepository):ViewModel() {

    var TAG="MyViewModel"

    private var loginResponseResult= MutableLiveData<LoginResult>()
    var loginMuthu= MutableLiveData<LoginResult>()
    val loginResult: MutableLiveData<LoginResult> = loginResponseResult

    private var loading = MutableLiveData<Boolean>()
    val loading1: MutableLiveData<Boolean> = loading
    var job: Job? = null
    suspend fun userLogin(email:String, password: String,action:String,fcm:String){
        loading.value=true

        job=viewModelScope.launch(Dispatchers.IO) {
            val result=repository.login(email,password,action,fcm)
           // delay(3000)
            if (result!=null){
                loginResult.postValue(result)
                loading.postValue(false)
                Log.e(TAG, "userLogin: $result")
            }
           else{
                Log.e(TAG, "userLogin-> data null: ")
            }

        }
    }

   fun complete(){

   }
}