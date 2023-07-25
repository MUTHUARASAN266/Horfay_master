package com.example.horfay123.`interface`

import com.example.horfay123.model.ListOfServiceData
import com.example.horfay123.model.VendorCartItemData

interface VendorList {
    fun onUnitItemClick(currentItem: ListOfServiceData)
}

interface OnClickListener {
    fun onClick(position: Int, model: VendorCartItemData)
}
interface OnItemCheckListener {
    fun onItemCheck(item: VendorCartItemData?)
    fun onItemUncheck(item: VendorCartItemData?)
}