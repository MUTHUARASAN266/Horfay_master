package com.example.horfay123.adapter

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.core.text.color
import androidx.recyclerview.widget.RecyclerView
import com.example.horfay123.databinding.ServiceOfferingListBinding
import com.example.horfay123.model.ServiceOfferData

class ServiceOfferAdapter(
    private val myData: List<ServiceOfferData>,
    private val listener: ServiceOfferClickListener
) : RecyclerView.Adapter<ServiceOfferAdapter.ViewHolder>() {

    val isChecked=false
    private var lastSelectedPosition = -1
    private val selectedItems = HashSet<Int>()
    inner class ViewHolder(val binding: ServiceOfferingListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.addButtonService.setOnClickListener {
                listener.onUnitItemClick(myData[adapterPosition])
            }
        }

        fun bind(data: ServiceOfferData) {
            binding.imageView.setImageResource(data.image)
            binding.textName.text = data.name
            binding.addButtonService.isChecked=data.isChecked
            binding.addButtonService.isChecked = myData[position].isChecked
            binding.addButtonService.setOnCheckedChangeListener { buttonView, isChecked ->
                myData[adapterPosition].isChecked = isChecked
                if (isChecked){
                    selectedItems.add(adapterPosition)
                }else{
                    selectedItems.remove(adapterPosition)
                }
            }


            if (lastSelectedPosition > 0) {
                myData[lastSelectedPosition].isChecked=false
            }

            // spannableString using for Bold and Text Color Changing
            val spannableStringBuilder = SpannableStringBuilder()
                .append("Price starts from ")
                .color(Color.BLACK) {
                    bold {
                        append(data.price)
                    }
                }
            binding.textPrice.text = spannableStringBuilder
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            ServiceOfferingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = myData[position]
        holder.bind(data)
        lastSelectedPosition = holder.adapterPosition

    }

    override fun getItemCount(): Int {
        return myData.size
    }


}

interface ServiceOfferClickListener {
    fun onUnitItemClick(currentItem: ServiceOfferData)
}
