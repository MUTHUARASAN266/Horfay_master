package com.example.horfay123.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.horfay123.R
import com.example.horfay123.`interface`.OnClickListener
import com.example.horfay123.`interface`.OnItemCheckListener
import com.example.horfay123.adapter.VendorCartItemAdapter
import com.example.horfay123.adapter.VendorItemAdapter
import com.example.horfay123.databinding.FragmentOurservicesBinding
import com.example.horfay123.model.VendorCartItemData
import com.example.horfay123.model.VendorItemData


class OurservicesFragment : Fragment(R.layout.fragment_ourservices) {
    var TAG="OurservicesFragment"
    lateinit var binding: FragmentOurservicesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentOurservicesBinding.inflate(inflater, container, false)


        binding.apply {
            vendorRecyclerview.layoutManager = GridLayoutManager(requireContext(), 3)
            vendorRecyclerview.hasFixedSize()
            val data = ArrayList<VendorItemData>()
            data.add(VendorItemData(R.drawable.image17, "Facial"))
            data.add(VendorItemData(R.drawable.image4, "Waxing"))
            data.add(VendorItemData(R.drawable.image_s15, "Facials & cleanups"))
            data.add(VendorItemData(R.drawable.image17, "Manicure"))
            data.add(VendorItemData(R.drawable.image4, "Pedicure"))
            data.add(VendorItemData(R.drawable.image_s15, "Threading"))
            vendorRecyclerview.setItemViewCacheSize(data.size)
            val adapter = VendorItemAdapter(data)
            adapter.setHasStableIds(true)
            adapter.notifyDataSetChanged()
            vendorRecyclerview.adapter = adapter

           //  vendor cart items
            vendorCartRecyclerview.layoutManager= LinearLayoutManager(requireContext())
            vendorCartRecyclerview.hasFixedSize()
            val vendorCartData=ArrayList<VendorCartItemData>()
            vendorCartData.add(VendorCartItemData(
                R.drawable.image19,
                "Water Based\nMakeup",
                "₹1499 onwards",
                "2 hrs",
                "Includes dummy info",
                "Includes dummy info"))
            vendorCartData.add(VendorCartItemData(
                R.drawable.image3,
                "Water Based\nMakeup",
                "₹1499 onwards",
                "2 hrs",
                "Includes dummy info",
                "Includes dummy info"))
            vendorCartData.add(VendorCartItemData(
                R.drawable.image19,
                "Water Based\nMakeup",
                "₹1199 onwards",
                "1 hrs",
                "Includes dummy info",
                "Includes dummy info"))
            vendorCartData.add(VendorCartItemData(
                R.drawable.image3,
                "Water Based\nMakeup",
                "₹1199 onwards",
                "1 hrs",
                "Includes dummy info",
                "Includes dummy info"))
            vendorCartRecyclerview.setItemViewCacheSize(vendorCartData.size)
            val vendorCartItemAdapter= VendorCartItemAdapter(vendorCartData)
            vendorCartItemAdapter.setHasStableIds(true)
            vendorCartItemAdapter.notifyDataSetChanged()
            vendorCartRecyclerview.adapter = vendorCartItemAdapter
            
            vendorCartItemAdapter.setOnClickListener(object : OnClickListener {
                override fun onClick(position: Int, model: VendorCartItemData) {
                 //  startActivity(Intent(context,ServicePageScreen::class.java))
                    if (model.isChecked){

                    }
                }

            })
            val currentSelectedItems=ArrayList<VendorCartItemData?>()
            vendorCartItemAdapter.setOnClickListener1(object : OnItemCheckListener {
                override fun onItemCheck(item: VendorCartItemData?) {
                    currentSelectedItems.add(item)
                    Log.e(TAG, "onItemCheck: ${currentSelectedItems.size}", )
                    buttonCheck(currentSelectedItems.size)
                    btnProceed.setOnClickListener {
                        startActivity(Intent(context,ServicePageScreen::class.java).putParcelableArrayListExtra("ServicePageScreen_Data",currentSelectedItems))
                    }
                }

                override fun onItemUncheck(item: VendorCartItemData?) {
                    currentSelectedItems.remove(item)
                    Log.e(TAG, "onItemUncheck: ${currentSelectedItems.size}", )
                    buttonCheck(currentSelectedItems.size)
                }

            })


        }


        return binding.root


    }

    private fun buttonCheck(currentSelectedItems: Int) {
        binding.apply {
            if (currentSelectedItems>0){
                binding.btnProceed.visibility=View.VISIBLE
            }else{
                binding.btnProceed.visibility=View.GONE
            }
        }
    }

}