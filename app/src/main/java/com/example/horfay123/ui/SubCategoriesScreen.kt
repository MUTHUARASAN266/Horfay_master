package com.example.horfay123.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.horfay123.R
import com.example.horfay123.adapter.SubCatAdapter
import com.example.horfay123.adapter.SubCatClickListener
import com.example.horfay123.databinding.ActivitySubCategoriesScreenBinding
import com.example.horfay123.model.SubCatData

class SubCategoriesScreen : AppCompatActivity(), SubCatClickListener {
    lateinit var binding: ActivitySubCategoriesScreenBinding
    val TAG = "SubCategoriesScreen"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubCategoriesScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            subRecycleView.layoutManager = GridLayoutManager(this@SubCategoriesScreen, 2)
            subRecycleView.hasFixedSize()
            val data = ArrayList<SubCatData>()
            data.add(SubCatData(R.drawable.image_s13, "Facial for glow"))
            data.add(SubCatData(R.drawable.image_s14, "Manicure"))
            data.add(SubCatData(R.drawable.image4, "Pediure"))
            data.add(SubCatData(R.drawable.image_s15, "Threading"))
            subRecycleView.setItemViewCacheSize(data.size)
            val myadapter = SubCatAdapter(data, this@SubCategoriesScreen)
            myadapter.setHasStableIds(true)
            subRecycleView.adapter = myadapter
            myadapter.notifyDataSetChanged()

            val data132 = intent.getStringExtra("UNIT_ID")
            binding.textViewAppbar.text = data132.toString()
            binding.textViewAppbar.textAlignment = View.TEXT_ALIGNMENT_TEXT_END


        }
    }

    override fun onResume() {
        super.onResume()
        val data = intent.getStringExtra("UNIT_ID")
        Log.e(TAG, "onResume:$data ")
    }

    override fun onUnitItemClick(currentItem: SubCatData) {
        Intent(this, ServiceOfferingScreen::class.java).apply {
            this.putExtra("UNIT_ID_service", currentItem.name)
            startActivity(this)
        }
    }
}
