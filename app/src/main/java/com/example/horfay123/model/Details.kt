package com.example.horfay123.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Details(
    @SerializedName("action")
    val action: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("created_at")
    val createdAt: Any,
    @SerializedName("date_joined")
    val dateJoined: String,
    @SerializedName("details")
    val details: DetailsX,
    @SerializedName("dob")
    val dob: Any,
    @SerializedName("email")
    val email: String,
    @SerializedName("expiry_time")
    val expiryTime: Any,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("groups")
    val groups: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_staff")
    val isStaff: Boolean,
    @SerializedName("is_superuser")
    val isSuperuser: Boolean,
    @SerializedName("last_login")
    val lastLogin: Any,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("otp")
    val otp: Any,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("pin")
    val pin: Any,
    @SerializedName("provider_status")
    val providerStatus: String,
    @SerializedName("updated_at")
    val updatedAt: Any,
    @SerializedName("user_permissions")
    val userPermissions: List<Any>,
    @SerializedName("username")
    val username: Any
)