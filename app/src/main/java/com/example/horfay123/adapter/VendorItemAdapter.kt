package com.example.horfay123.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horfay123.databinding.VendorProfileItemsBinding
import com.example.horfay123.model.VendorItemData

class VendorItemAdapter(private val myData: List<VendorItemData>):RecyclerView.Adapter<VendorItemAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: VendorProfileItemsBinding):
        RecyclerView.ViewHolder(binding.root){

            fun bind(myData: VendorItemData){
                binding.vendorItemImg.setImageResource(myData.image)
                binding.vendoritemText.text=myData.itemName
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = VendorProfileItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=myData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return myData.size
    }
}