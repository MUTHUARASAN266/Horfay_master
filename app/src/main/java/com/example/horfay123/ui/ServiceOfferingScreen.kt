package com.example.horfay123.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.horfay123.R
import com.example.horfay123.adapter.ServiceOfferAdapter
import com.example.horfay123.adapter.ServiceOfferClickListener
import com.example.horfay123.databinding.ActivityServiceOfferingScreenBinding
import com.example.horfay123.model.ServiceOfferData
import com.example.horfay123.toast

class ServiceOfferingScreen : AppCompatActivity(), ServiceOfferClickListener {
    var TAG = "ServiceOfferingScreen"
    lateinit var binding: ActivityServiceOfferingScreenBinding
    lateinit var adapter: ServiceOfferAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceOfferingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonProceed.visibility=View.GONE
        binding.apply {


            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            recyclerviewServiceOffer.layoutManager =
                GridLayoutManager(this@ServiceOfferingScreen, 2)
            recyclerviewServiceOffer.setHasFixedSize(true)
            val myData = ArrayList<ServiceOfferData>()
            myData.add(ServiceOfferData(R.drawable.image17, "Pearl Facial", "₹199 Only"))
            myData.add(ServiceOfferData(R.drawable.image18, "Gold facial", "₹150 Only"))
            myData.add(ServiceOfferData(R.drawable.image16, "Diamond Facial", "₹149 Only"))
            adapter= ServiceOfferAdapter(myData, this@ServiceOfferingScreen)
            adapter.setHasStableIds(true)
            recyclerviewServiceOffer.adapter = adapter
        }

        val data = intent.getStringExtra("UNIT_ID_service")
        binding.textViewAppbar.text = data.toString()
        Log.e(TAG, "onCreate: $data")
    }

    override fun onUnitItemClick(currentItem: ServiceOfferData) {
        binding.apply {
            if (currentItem.isChecked){
                buttonProceed.visibility=View.VISIBLE
                buttonProceed.setOnClickListener {
                    Intent(this@ServiceOfferingScreen, SelectedServiceScreen::class.java).apply {
                        this.putExtra("data_service", currentItem)
                        startActivity(this)
                    }
                }
            }else{
                buttonProceed.visibility=View.GONE
                toast("please select")

            }

        }
       /* Intent(this, SelectedServiceScreen::class.java).apply {
            this.putExtra("data_service", currentItem)
            startActivity(this)
        }*/
        // startActivity(Intent(this,SelectedServiceScreen::class.java).putExtra("data1",currentItem))
    }
}
