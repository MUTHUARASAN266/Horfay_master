package com.example.horfay123.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.horfay123.R
import com.example.horfay123.adapter.ImageSliderAdapter
import com.example.horfay123.adapter.VendorCartItemAdapter
import com.example.horfay123.adapter.VendorHeaderItemsAdapter
import com.example.horfay123.adapter.VendorItemAdapter
import com.example.horfay123.databinding.ActivityVendorProfileScreenBinding
import com.example.horfay123.model.VendorCartItemData
import com.example.horfay123.model.VendorHeaderItemData
import com.example.horfay123.model.VendorItemData
import com.smarteist.autoimageslider.SliderView

class VendorProfileScreen : AppCompatActivity() {
    private lateinit var sliderAdapter: ImageSliderAdapter
    private lateinit var imageSliter: ArrayList<Int>
    lateinit var binding: ActivityVendorProfileScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVendorProfileScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            backArrow.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }


//            vendorRecyclerview.layoutManager = GridLayoutManager(this@VendorProfileScreen, 3)
//            vendorRecyclerview.hasFixedSize()
//            val data = ArrayList<VendorItemData>()
//            data.add(VendorItemData(R.drawable.image17, "Facial"))
//            data.add(VendorItemData(R.drawable.image4, "Waxing"))
//            data.add(VendorItemData(R.drawable.image_s15, "Facials & cleanups"))
//            data.add(VendorItemData(R.drawable.image17, "Manicure"))
//            data.add(VendorItemData(R.drawable.image4, "Pedicure"))
//            data.add(VendorItemData(R.drawable.image_s15, "Threading"))
//            vendorRecyclerview.setItemViewCacheSize(data.size)
//            val adapter = VendorItemAdapter(data)
//            adapter.setHasStableIds(true)
//            adapter.notifyDataSetChanged()
//            vendorRecyclerview.adapter = adapter

            // set arrayList
            imageSliter = ArrayList()
            imageSliter.add(R.drawable.image_muthu)
            imageSliter.add(R.drawable.image_muthu)

            sliderAdapter = ImageSliderAdapter(imageSliter)

            vendorHeaderRecyclerview.layoutManager = LinearLayoutManager(this@VendorProfileScreen)
            vendorHeaderRecyclerview.hasFixedSize()
            val vendorHeaderData = ArrayList<VendorHeaderItemData>()
            vendorHeaderData.add(
                VendorHeaderItemData(
                    R.drawable.image_muthu,
                    "R Spa",
                    "4.8  (12k)",
                    "213 bookings this year in Jammu Surinsar Mansar Road"
                )
            )
            vendorHeaderRecyclerview.setItemViewCacheSize(vendorHeaderData.size)
            val vendorHeaderItemsAdapter = VendorHeaderItemsAdapter(vendorHeaderData)
            vendorHeaderItemsAdapter.setHasStableIds(true)
            vendorHeaderItemsAdapter.notifyDataSetChanged()
            vendorHeaderRecyclerview.adapter = vendorHeaderItemsAdapter




//            // vendor cart items
//            vendorCartRecyclerview.layoutManager=LinearLayoutManager(this@VendorProfileScreen)
//            vendorCartRecyclerview.hasFixedSize()
//            val vendorCartData=ArrayList<VendorCartItemData>()
//            vendorCartData.add(VendorCartItemData(R.drawable.image19,
//                "Water Based\nMakeup",
//                "₹1499 onwards",
//                "2 hrs",
//                "Includes dummy info",
//                "Includes dummy info"))
//            vendorCartData.add(VendorCartItemData(
//                R.drawable.image3,
//                "Water Based\nMakeup",
//                "₹1499 onwards",
//                "2 hrs",
//                "Includes dummy info",
//                "Includes dummy info"))
//            vendorCartData.add(VendorCartItemData(R.drawable.image19,
//                "Water Based\nMakeup",
//                "₹1199 onwards",
//                "1 hrs",
//                "Includes dummy info",
//                "Includes dummy info"))
//            vendorCartData.add(VendorCartItemData(
//                R.drawable.image3,
//                "Water Based\nMakeup",
//                "₹1199 onwards",
//                "1 hrs",
//                "Includes dummy info",
//                "Includes dummy info"))
//            vendorCartRecyclerview.setItemViewCacheSize(vendorCartData.size)
//            val vendorCartItemAdapter=VendorCartItemAdapter(vendorCartData)
//            vendorCartItemAdapter.setHasStableIds(true)
//            vendorCartItemAdapter.notifyDataSetChanged()
//            vendorCartRecyclerview.adapter = vendorCartItemAdapter

            /*

               sliderVendor.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR

               sliderVendor.setSliderAdapter(sliderAdapter)
               // 3 seconds for our slider view.
               sliderVendor.scrollTimeInSec = 3

               // auto slide
               sliderVendor.isAutoCycle = true

               sliderVendor.startAutoCycle()

   */


        }
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            buttonAbout.setOnClickListener {
                findNavController(R.id.fragmentContainerView_muthu).navigate(R.id.aboutUsServiceFragment)
                buttonAbout.backgroundTintList=ContextCompat.getColorStateList(this@VendorProfileScreen,R.color.black)
                buttonAbout.setTextColor(ContextCompat.getColor(this@VendorProfileScreen,R.color.white))
                buttonService.backgroundTintList=ContextCompat.getColorStateList(this@VendorProfileScreen,R.color.gray)
                buttonService.setTextColor(ContextCompat.getColor(this@VendorProfileScreen,R.color.white))
            }
            buttonService.setOnClickListener {
                findNavController(R.id.fragmentContainerView_muthu).navigate(R.id.ourservicesFragment)
                buttonAbout.backgroundTintList=ContextCompat.getColorStateList(this@VendorProfileScreen,R.color.gray)
                buttonAbout.setTextColor(ContextCompat.getColor(this@VendorProfileScreen,R.color.white))
                buttonService.backgroundTintList=ContextCompat.getColorStateList(this@VendorProfileScreen,R.color.black)
                buttonService.setTextColor(ContextCompat.getColor(this@VendorProfileScreen,R.color.white))
            }
        }
    }
}