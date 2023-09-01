package com.example.horfay123.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horfay123.databinding.SelectAddresItemBinding
import com.example.horfay123.`interface`.AddressBottomSheetListener
import com.example.horfay123.model.AddressItemBot
import com.example.horfay123.model.AddressItemBotFull


class AddressBottomAdapter(private val myData: List<AddressItemBot>) :
    RecyclerView.Adapter<AddressBottomAdapter.ViewHolder>() {
    private var listData: MutableList<AddressItemBot> = myData as MutableList<AddressItemBot>

    private var lastSelectedPosition = -1
    private var listener: AddressBottomSheetListener? = null

    inner class ViewHolder(val binding: SelectAddresItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(addressItemBot: AddressItemBot) {
            binding.addressText.text = addressItemBot.address
            binding.checkBox.text = addressItemBot.title

            // checkBox ItemClick
            binding.checkBox.setOnClickListener {
                lastSelectedPosition = adapterPosition
                notifyDataSetChanged()
                listener?.onItemClick(position, addressItemBot, binding.checkBox.isChecked)
            }
            binding.deleteButton.setOnClickListener {
                //  deleteItem(position)
                notifyDataSetChanged()
                listener?.onItemDelete(position, addressItemBot, binding.checkBox.isChecked)
            }

        }

    }
    fun addNewItem(item: AddressItemBot) {
        val insertPosition = listData.size
        listData.addAll(listOf(item))
        //notifyItemRangeInserted(insertPosition, item.size)
    }
    private fun deleteItem(position: Int) {
        listData.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            SelectAddresItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return myData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = myData[position]
        holder.bind(data)
        holder.binding.checkBox.isChecked = lastSelectedPosition == position
    }

    // checkBox ItemClick
    fun setOnClickListener1(addressBottomSheetListener: AddressBottomSheetListener) {
        this.listener = addressBottomSheetListener
    }

}