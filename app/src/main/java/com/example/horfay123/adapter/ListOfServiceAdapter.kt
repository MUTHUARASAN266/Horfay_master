package com.example.horfay123.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.core.text.color
import androidx.recyclerview.widget.RecyclerView
import com.example.horfay123.`interface`.VendorList
import com.example.horfay123.databinding.ServiceOfferingVendorListBinding
import com.example.horfay123.model.ListOfServiceData


class ListOfServiceAdapter(private val myData: List<ListOfServiceData>,private val listener: VendorList) :
    RecyclerView.Adapter<ListOfServiceAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ServiceOfferingVendorListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                vendorTextProviderDes1.setOnClickListener {
                    listener.onUnitItemClick(myData[adapterPosition])
                }
                buttonListofProvider.setOnClickListener {
                    //listener.onUnitItemClickPageScreen(myData[adapterPosition])
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun Bind(data: ListOfServiceData) {
            binding.vendorTextProviderName.text = data.provider_name
            data.image?.let { binding.vendorProviderImg.setImageResource(it) }
            binding.vendorTextRatingProvider.text = data.rating
            binding.vendorTextProviderJob.text = data.job

            // spannableString using for Bold and Text Color Changing
            val spannableStringBuilder = SpannableStringBuilder()
                .append("Price starts from ")
                .color(Color.BLACK) {
                    bold {
                        append(data.price)
                    }
                }
            binding.vendorTextProviderPrice.text = spannableStringBuilder
            binding.vendorTextProviderDes.text = data.provider_des
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = ServiceOfferingVendorListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = myData[position]
        holder.Bind(data)
    }

    override fun getItemCount(): Int {
        return myData.size
    }
}
