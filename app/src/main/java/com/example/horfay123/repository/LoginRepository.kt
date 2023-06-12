package com.example.horfay123.repository

import DataStateResult
import LoginResult
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.horfay123.model.SignUpData
import com.example.horfay123.model.SignUpResponse
import com.example.horfay123.network.ApiService
import com.example.horfay123.user
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject

class LoginRepository(val apiService: ApiService) {
    val TAG="LoginRepository"

   /* suspend fun login1(number:String,password:String){
        val responce=apiService.login(number,password)
        if (responce.isSuccessful){
            responce.body()
        }else{
            responce.message()
        }
    }*/

   /* suspend fun getLogin(number:String,password:String): Response<LoginData> {
        return apiService.login(number,password)
    }*/

    suspend fun login(email: String, password: String,action:String,fcm:String):  LoginResult {
        val loginResponse = MutableLiveData<String>()
        return try {

            val response =apiService.login(email,password,action,fcm)
            if (response.isSuccessful) {
              //  Log.e(TAG, "login response: ${response.body()}")
                val body=response.body()
              //  Log.e(TAG, "login->: $body")
                if (body==null){
                 //   Log.e(TAG, "login response: response.body() null")
                    LoginResult.Error("response.body() null")
                }
                Log.e(TAG, "login1: $body")
                LoginResult.Success(body)

            } else {
              //  val error=response.message()
                val jObjError = JSONObject(response.errorBody()?.source().toString())
                val errorMessage = jObjError.get("message")

                Log.e(TAG, "login->error: $errorMessage")
                Log.e(TAG, "login->error: ${response.errorBody()?.source().toString()}")
                LoginResult.Error("errorMessage")

            }
        } catch (e: Exception) {
            Log.e(TAG, "muthuLog: ${e.message}")
            LoginResult.Error("${e.message}")
        }

    }



    /*
    suspend fun signUp1(userdata:SignUpData): Flow<DataStateResult<SignUpResponse>> =
        flow {
            apiService.signUp(userdata).collect {
                when (it) {
                    is DataStateResult.Error -> {
                        emit(DataStateResult.Error(it.exception))
                    }
                    is DataStateResult.Loading -> {
                        emit(DataStateResult.Loading(it.loading))
                    }
                    is DataStateResult.Success -> {
                        emit(DataStateResult.Success(it.data))
                    }
                }
            }
        }
    */
}