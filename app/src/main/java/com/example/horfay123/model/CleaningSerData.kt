package com.example.horfay123.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class CleaningSerData(
    val image: Int?,
    val name: String?
)

data class AllCatData(
    val image: Int?,
    val name: String?

)

data class SubCatData(
    val image: Int?,
    val name: String?

)

@Parcelize
data class ServiceOfferData(
    val image: Int?,
    val name: String?,
    val price: String?,
    var isChecked:Boolean=false
) : Parcelable

data class SelectServiceData(
    val image: Int?,
    val service_name: String?,
    val price: String?,
    val mins: String?,
    val service_des1: String?,
    val service_des2: String?
)

data class SelectProviterData(
    val image: Int?,
    val provider_name: String?,
    val rating: String?,
    val job: String?,
    val price: String?,
    val provider_des1: String?
)

data class ListOfServiceData(
    val image: Int?,
    val provider_name: String?,
    val rating: String?,
    val job: String?,
    val price: String?,
    val provider_des: String?
)
data class VendorItemData(
    val image: Int?,
    val itemName:String?
)
data class VendorHeaderItemData(
    val image:Int?,
    val itemName:String?,
    val rating: String?,
    val job: String?
)data class VendorCartItemData(
    val image:Int?,
    val mackUpName:String?,
    val amount:String?,
    val hours:String?,
    val info_one: String?,
    val info_two: String?,
    val isChecked: Boolean = false
)


