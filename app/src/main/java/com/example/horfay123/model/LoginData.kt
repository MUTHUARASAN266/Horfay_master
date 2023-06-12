package com.example.horfay123.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LoginData(
    @SerializedName("Details")
    val details: Details,
    @SerializedName("kyc")
    val kyc: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("status_code")
    val statusCode: Int
)