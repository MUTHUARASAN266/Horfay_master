package com.example.horfay123.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horfay123.databinding.CleaningServicesListBinding
import com.example.horfay123.model.CleaningSerData

class CleaningServicesAdapter(var myData: List<CleaningSerData>) :
    RecyclerView.Adapter<CleaningServicesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CleaningServicesListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CleaningSerData) {
            binding.serviceImg.setImageResource(data.image)
            binding.serviceText.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutBinding =
            CleaningServicesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(layoutBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(myData[position])

    }

    override fun getItemCount(): Int {
        return myData.size
    }
}
