package com.example.horfay123.network

import com.example.horfay123.model.LoginData
import com.example.horfay123.model.SignUpData
import com.example.horfay123.model.SignUpResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("logininfo/")
    suspend fun login(@Field("phone_number") phone_number: String, @Field("password") password: String, @Field("action") action:String,@Field("fcm") fcm:String):Response<LoginData>

    @POST("userinfo/create/")
    suspend fun signUp(@Body userData:SignUpData): Response<SignUpResponse>


    @FormUrlEncoded
    @POST("userinfo/create/")
     fun signUp2(@Field("password") password: String,
                 @Field("username") username: String,
                 @Field("phone_number") phone_number:String,
                 @Field("city") city: String,
                 @Field("email") email: String,
                 @Field("last_name") last_name:String,
                 @Field("first_name") first_name: String,
                 @Field("country") country: String,
                 @Field("action") action:String
    ): Call<SignUpResponse>

    @POST("userinfo/create/")
    suspend fun signUp1(@Field("password") password: String,
                        @Field("username") username: String,
                        @Field("phone_number") phone_number:String,
                        @Field("city") city: String,
                        @Field("email") email: String,
                        @Field("last_name") last_name:String,
                        @Field("first_name") first_name: String,
                        @Field("country") country: String,
                        @Field("action") action:String
    ): Response<SignUpResponse>
}