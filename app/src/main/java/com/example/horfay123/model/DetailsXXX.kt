package com.example.horfay123.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DetailsXXX(
    @SerializedName("About_Me")
    val aboutMe: String,
    @SerializedName("Additional_Information")
    val additionalInformation: String,
    @SerializedName("Bank_Details")
    val bankDetails: String,
    @SerializedName("Business_Information")
    val businessInformation: String,
    @SerializedName("My_Documents")
    val myDocuments: String,
    @SerializedName("My_Services")
    val myServices: String
)