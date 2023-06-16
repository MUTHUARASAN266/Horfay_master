package com.example.horfay123.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.horfay123.databinding.VendorProfileHeaderItemsBinding
import com.example.horfay123.model.VendorHeaderItemData

class VendorHeaderItemsAdapter(private val myData:List<VendorHeaderItemData>):RecyclerView.Adapter<VendorHeaderItemsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding:VendorProfileHeaderItemsBinding)
        :RecyclerView.ViewHolder(binding.root){
        private val imageList = ArrayList<SlideModel>()
            fun bind(myData:VendorHeaderItemData){
                binding.vendorName.text=myData.itemName
                binding.vendorRating.text=myData.rating
                binding.vendorJob.text=myData.job
                imageList.add(SlideModel(myData.image,ScaleTypes.FIT))
                imageList.add(SlideModel(myData.image,ScaleTypes.FIT))
                binding.imageSlider.setImageList(imageList = imageList)

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=VendorProfileHeaderItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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