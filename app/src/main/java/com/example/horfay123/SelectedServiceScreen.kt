package com.example.horfay123

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.horfay123.adapter.ImageSliderAdapter
import com.example.horfay123.adapter.SelectServiceAdapter
import com.example.horfay123.databinding.ActivitySelectedServiceScreenBinding
import com.example.horfay123.model.SelectProviterData
import com.example.horfay123.model.SelectServiceData
import com.example.horfay123.model.ServiceOfferData
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarteist.autoimageslider.SliderView
import com.smarteist.autoimageslider.SliderViewAdapter

class SelectedServiceScreen : AppCompatActivity() {
    val TAG = "SelectedServiceScreen"
    lateinit var sliderAdapter: ImageSliderAdapter
    lateinit var image:ArrayList<Int>
    lateinit var binding: ActivitySelectedServiceScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedServiceScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val people: ServiceOfferData? = intent.getParcelableExtra("data_service")
        Log.e(TAG, "onCreate:${people?.name}")
        Log.e(TAG, "onCreate:${people?.price}")

        binding.apply {
            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            buttonSelectService.setOnClickListener {
                val bottomSheetDialog: BottomSheetDialog =
                    BottomSheetDialog(this@SelectedServiceScreen,R.style.CustomBottomSheetDialogTheme)
                val view = bottomSheetDialog.layoutInflater.inflate(
                    R.layout.sub_categories_bootomsheet,
                    null
                )
                view.findViewById<Button>(R.id.button_list_ser_pro).setOnClickListener {
                    startActivity(Intent(this@SelectedServiceScreen, ServiceListScreen::class.java))
                    bottomSheetDialog.dismiss()
                }

                // initialize slider view
                val slider=view.findViewById<SliderView>(R.id.slider)

                // set arrayList
                image= ArrayList()
                image.add(R.drawable.image_muthu)
                image.add(R.drawable.image_muthu)

                sliderAdapter=ImageSliderAdapter(image)

                slider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR

                slider.setSliderAdapter(sliderAdapter)
                // 3 seconds for our slider view.
                slider.scrollTimeInSec = 3

                // auto slide
                slider.isAutoCycle = true

                slider.startAutoCycle()

                val vbv=view.findViewById<LinearLayout>(R.id.muthu01_layout)
                val behavior = BottomSheetBehavior.from(vbv)

                bottomSheetDialog.setCancelable(true)
                bottomSheetDialog.setContentView(view)
                bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
                bottomSheetDialog.show()
            }

            recyclerviewSelectService.layoutManager =
                LinearLayoutManager(this@SelectedServiceScreen)
            recyclerviewSelectService.setHasFixedSize(true)
            val value = ArrayList<SelectServiceData>()
            value.add(
                SelectServiceData(
                    people!!.image,
                    people.name,
                    "₹599",
                    "45 mins",
                    "For all skin types. Pinacolada mask.",
                    "6-step process. Includes 10-min massage"
                )
            )
            value.add(
                SelectServiceData(
                    people.image,
                    people.name,
                    "₹799",
                    "40 mins",
                    "For all skin types. Pinacolada mask.",
                    "6-step process. Includes 15-min massage"
                )
            )

            val providerData = ArrayList<SelectProviterData>()
            providerData.add(
                SelectProviterData(
                    R.drawable.make_up1,
                    "Matikaas Beauty Lounge",
                    "4.8",
                    "89 jobs completed",
                    "Price  ₹699",
                    "Specialises in Airbrush Make-up"
                )
            )
            providerData.add(
                SelectProviterData(
                    R.drawable.make_up1,
                    "Matikaas Beauty Lounge1",
                    "4.7",
                    "99 jobs completed",
                    "Price  ₹999",
                    "Specialises in Airbrush Make-up"
                )
            )
            val myAdapter = SelectServiceAdapter(value, providerData)
            myAdapter.setHasStableIds(true)
            recyclerviewSelectService.adapter = myAdapter

        }
    }
}
