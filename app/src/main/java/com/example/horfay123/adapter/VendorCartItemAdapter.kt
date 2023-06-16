package com.example.horfay123.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horfay123.databinding.VendorProfileAddItemsBinding
import com.example.horfay123.model.VendorCartItemData

class VendorCartItemAdapter(private val myData:List<VendorCartItemData>):RecyclerView.Adapter<VendorCartItemAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: VendorProfileAddItemsBinding)
        :RecyclerView.ViewHolder(binding.root){

            fun bind(myData:VendorCartItemData){
                binding.vendroCartImage.setImageResource(myData.image)
                binding.nameText.text=myData.mackUpName
                binding.amountText.text=myData.amount
                binding.hoursText.text=myData.hours
                binding.infoOneText.text=myData.info_one
                binding.infoTwoText.text=myData.info_two
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=VendorProfileAddItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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