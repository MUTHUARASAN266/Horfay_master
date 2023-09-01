package com.example.horfay123.`interface`

import com.example.horfay123.model.AddressItemBot
import com.example.horfay123.model.ListOfServiceData
import com.example.horfay123.model.VendorCartItemData

interface VendorList {
    fun onUnitItemClick(currentItem: ListOfServiceData)
    fun onUnitItemClickPageScreen(currentItem: ListOfServiceData)
}

interface OnClickListener {
    fun onClick(position: Int, model: VendorCartItemData)
}
interface OnItemCheckListener {
    fun onItemCheck(item: VendorCartItemData?)
    fun onItemUncheck(item: VendorCartItemData?)
}
interface AddressBottomSheetListener {
    fun onItemClick(position: Int,addressItemBot: AddressItemBot,isSelect:Boolean)
    fun onItemDelete(position: Int,addressItemBot: AddressItemBot,isSelect:Boolean)
}