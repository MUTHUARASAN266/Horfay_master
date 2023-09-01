package com.example.horfay123.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horfay123.databinding.SelectServiceListItemsBinding
import com.example.horfay123.model.VendorCartItemData

class SelectedServiceAdapter(private val myData: List<VendorCartItemData>) :
    RecyclerView.Adapter<SelectedServiceAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: SelectServiceListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: VendorCartItemData) {
            binding.apply {
                data.apply {
                    image?.let { imageviewSsList.setImageResource(it) }
                    titleSsList.text = mackUpName
                    amountSsList.text = amount
                    textMinSsList.text = hours
                    textDes1SsList.text = info_one
                    textDes2SsList.text = info_two
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = SelectServiceListItemsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = myData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return myData.size
    }

}
