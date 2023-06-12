package com.example.horfay123.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horfay123.databinding.SubCategorieListBinding
import com.example.horfay123.model.SubCatData

class SubCatAdapter(
    private val myData: List<SubCatData>,
    private val listener: SubCatClickListener
) : RecyclerView.Adapter<SubCatAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: SubCategorieListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.subCatContainer.setOnClickListener {
                listener.onUnitItemClick(myData[adapterPosition])
            }
        }

        fun bind(myData: SubCatData) {
            binding.subImg.setImageResource(myData.image)
            binding.subText.text = myData.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            SubCategorieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

interface SubCatClickListener {
    fun onUnitItemClick(currentItem: SubCatData)
}
