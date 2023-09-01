package com.example.horfay123.adapter

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horfay123.`interface`.OnClickListener
import com.example.horfay123.`interface`.OnItemCheckListener
import com.example.horfay123.databinding.VendorProfileAddItemsBinding
import com.example.horfay123.model.VendorCartItemData


class VendorCartItemAdapter(private val myData: List<VendorCartItemData>) :
    RecyclerView.Adapter<VendorCartItemAdapter.ViewHolder>() {
    private var onClickListener: OnClickListener? = null
    private var onClickListenerCart: OnItemCheckListener? = null

    inner class ViewHolder(private val binding: VendorProfileAddItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(myData: VendorCartItemData) {
            myData.image?.let { binding.vendroCartImage.setImageResource(it) }
            binding.nameText.text = myData.mackUpName
            binding.amountText.text = myData.amount
            binding.hoursText.text = myData.hours
            binding.infoOneText.text = myData.info_one
            binding.infoTwoText.text = myData.info_two

            binding.addCartButton1.setOnClickListener {

                if (binding.addCartButton1.isChecked) {
                    onClickListenerCart?.onItemCheck(myData)
                } else {
                    onClickListenerCart?.onItemUncheck(myData)
                }

//                    if (onClickListener!=null){
//                        onClickListener!!.onClick(adapterPosition,myData)
//                    }
            }
        }

        init {

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            VendorProfileAddItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = myData[position]
        holder.bind(data)

    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    fun setOnClickListener1(onItemCheckListener: OnItemCheckListener) {
        this.onClickListenerCart = onItemCheckListener
    }

    override fun getItemCount(): Int {
        return myData.size
    }
}
