package com.example.horfay123.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horfay123.databinding.AllCategoriesListBinding
import com.example.horfay123.model.AllCatData

class AllCategoriesAdapter(
    private val myData: List<AllCatData>,
    private val listener: UnitClickListener
) : RecyclerView.Adapter<AllCategoriesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AllCategoriesListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.container.setOnClickListener {
                listener.onUnitItemClick(myData[adapterPosition])
            }
        }

        fun bind(mydata: AllCatData) {
            mydata.image?.let { binding.catImg1.setImageResource(it) }
            binding.catText1.text = mydata.name
            /*binding.catImg2.setImageResource(mydata.image)
            binding.catText2.text=mydata.name
            binding.catImg3.setImageResource(mydata.image)
            binding.catText3.text=mydata.name*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            AllCategoriesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

interface UnitClickListener {
    fun onUnitItemClick(currentItem: AllCatData)
}
