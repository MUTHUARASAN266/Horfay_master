package com.example.horfay123.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SignUpData(
    @SerializedName("action")
    val action: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("username")
    val username: String
)