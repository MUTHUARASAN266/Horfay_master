package com.example.horfay123.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horfay123.databinding.SelectedServiceListBinding
import com.example.horfay123.model.SelectProviterData
import com.example.horfay123.model.SelectServiceData

class SelectServiceAdapter(
    private val myData: List<SelectServiceData>,
    private val myData1: List<SelectProviterData>
) : RecyclerView.Adapter<SelectServiceAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: SelectedServiceListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(myData: SelectServiceData, pData: SelectProviterData) {


            //ServiceText
            binding.textServiceName.paintFlags =
                binding.textServiceName.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            binding.textServiceName.text = myData.service_name
            binding.imgService.setImageResource(myData.image)
            binding.textPrice.text = myData.price
            binding.textServiceDes1.text = myData.mins
            binding.textServiceDes2.text = myData.service_des1
            binding.textServiceDes3.text = myData.service_des2

            //ProviderText
            binding.textProviderName.paintFlags =
                binding.textProviderName.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            binding.textProviderName.text = pData.provider_name
            binding.providerImg.setImageResource(pData.image)
            binding.textRatingProvider.text = pData.rating
            binding.textProviderJob.text = pData.job
            binding.textProviderPrice.text = pData.price
            binding.textProviderDes.text = pData.provider_des1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            SelectedServiceListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = myData[position]
        val data1 = myData1[position]
        holder.bind(data, data1)
    }

    override fun getItemCount(): Int {
        return myData.size
    }
}
