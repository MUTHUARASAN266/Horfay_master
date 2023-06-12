package com.example.horfay123.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SignUpResponse(
    @SerializedName("details")
    val details: DetailsXX,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)