package com.example.horfay123.repository

import android.service.autofill.UserData
import com.example.horfay123.model.SignUpData
import com.example.horfay123.model.SignUpResponse
import com.example.horfay123.network.ApiService

class SignUpRepository(private val apiService: ApiService) {
    val TAG="SignUpRepository"

    suspend fun signUp(userData: SignUpData):SignUpResponse?{

        val response = apiService.signUp(userData)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
    suspend fun signUp1(password: String,username: String,phone: String,city: String,email: String,lastName: String,firstName: String,country: String,action: String):SignUpResponse?{
        //val res=loginRequest
        //val request=SignUpData("User","kasmir","india","uio11p0@yopmail.com","raiyan","mohhmed","User@123","+9110000 88880","mohamed",)
       // val response = apiService.signUp1(a,b,c,e,d,f,g,h,i)
        val response = apiService.signUp1(password, username,phone, city, email,lastName,firstName, country, action)
        // val response = apiService.signUp(loginRequest)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}