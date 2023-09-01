package com.example.horfay123.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.horfay123.R
import com.example.horfay123.`interface`.VendorList
import com.example.horfay123.adapter.ListOfServiceAdapter
import com.example.horfay123.databinding.ActivityServiceListScreenBinding
import com.example.horfay123.model.ListOfServiceData
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class ServiceListScreen : AppCompatActivity(),VendorList {
    lateinit var binding: ActivityServiceListScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceListScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            backArrow.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            recyclerviewServiceList.layoutManager = LinearLayoutManager(this@ServiceListScreen)
            recyclerviewServiceList.setHasFixedSize(true)
            val value = ArrayList<ListOfServiceData>()
            value.add(
                ListOfServiceData(
                    R.drawable.make_up1,
                    "Matikaas Beauty Lounge",
                    "4.8",
                    "88 jobs completed",
                    "-₹599",
                    "Specialises in Airbrush Make-up"
                )
            )
            value.add(
                ListOfServiceData(
                    R.drawable.make_up1,
                    "Jawed Habib Parlour",
                    "4.2",
                    "120 jobs completed",
                    "-₹500",
                    "Specialises in Airbrush Make-up"
                )
            )
            value.add(
                ListOfServiceData(
                    R.drawable.make_up1,
                    "Makeover Gogji Bagh",
                    "4.0",
                    "75 jobs completed",
                    "-₹600",
                    "Specialises in Airbrush Make-up"
                )
            )
               value.add(
                ListOfServiceData(
                    R.drawable.make_up1,
                    "Makeover Gogji Bagh",
                    "4.5",
                    "50 jobs completed",
                    "-₹900",
                    "Specialises in Airbrush Make-up"
                )
            )

            val myAdapter = ListOfServiceAdapter(value,this@ServiceListScreen)
            myAdapter.setHasStableIds(true)
            recyclerviewServiceList.adapter = myAdapter

            imgFilter.setOnClickListener {
                val bottomSheetDialog=BottomSheetDialog(this@ServiceListScreen,
                    R.style.CustomBottomSheetDialogTheme
                )
                val view=bottomSheetDialog.layoutInflater.inflate(R.layout.filter_bottomsheet,null)
                val vbv=view.findViewById<LinearLayout>(R.id.muthu_layout_filter)
                val behavior = BottomSheetBehavior.from(vbv)
                bottomSheetDialog.setContentView(view)
                bottomSheetDialog.behavior.state=BottomSheetBehavior.STATE_COLLAPSED
                bottomSheetDialog.setCancelable(true)
                bottomSheetDialog.show()
            }
        }
    }

    override fun onUnitItemClick(currentItem: ListOfServiceData) {
        startActivity(Intent(this, ServiceOfferingCardScreen::class.java))
    }

    override fun onUnitItemClickPageScreen(currentItem: ListOfServiceData) {
        startActivity(Intent(this, ServicePageScreen::class.java))
    }
}
