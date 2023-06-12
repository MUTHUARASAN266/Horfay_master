package com.example.horfay123

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.horfay123.adapter.AllCategoriesAdapter
import com.example.horfay123.adapter.UnitClickListener
import com.example.horfay123.databinding.FragmentAllCategoriesBinding
import com.example.horfay123.model.AllCatData

class AllCategories : Fragment(R.layout.fragment_all_categories), UnitClickListener {
    lateinit var binding: FragmentAllCategoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllCategoriesBinding.inflate(inflater, container, false)

        binding.apply {
            backArrow.setOnClickListener {
                this@AllCategories.findNavController().popBackStack()

            }

            //allCategoriesRecyclerview.layoutManager=GridLayoutManager(context,RecyclerView.VERTICAL,false)
            allCategoriesRecyclerview.layoutManager = GridLayoutManager(context, 3)
            allCategoriesRecyclerview.setHasFixedSize(true)
            val myData = ArrayList<AllCatData>()
            myData.add(AllCatData(R.drawable.appleorchard, "Apple orchard development"))
            myData.add(AllCatData(R.drawable.appliance_repair, "Appliance repair & service"))
            myData.add(AllCatData(R.drawable.architect, "Architect"))
            myData.add(AllCatData(R.drawable.cleaning_pest, "Cleaning Pest Control"))
            myData.add(AllCatData(R.drawable.electrician_plug, "Electrician"))
            myData.add(AllCatData(R.drawable.home_paints, "Home Paints & cladding"))
            myData.add(AllCatData(R.drawable.interior_design, "Interior design"))
            myData.add(AllCatData(R.drawable.landscape_g, "Landscape & gardening"))
            myData.add(AllCatData(R.drawable.khatamband, "Khatamband"))
            myData.add(AllCatData(R.drawable.mens_salon, "Men’s salon and massage"))
            myData.add(AllCatData(R.drawable.plumbers, "Plumbers"))
            myData.add(AllCatData(R.drawable.wedding_services, "Wedding services"))
            myData.add(AllCatData(R.drawable.wazwan, "Wazwan"))
            myData.add(AllCatData(R.drawable.women_salon, "Women’s salon and spa"))
            // allCategoriesRecyclerview.setItemViewCacheSize(myData.size)
            val adapter = AllCategoriesAdapter(myData, this@AllCategories)
            adapter.setHasStableIds(true)
            allCategoriesRecyclerview.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }

    override fun onUnitItemClick(currentItem: AllCatData) {
        Intent(activity, SubCategoriesScreen::class.java).apply {
            this.putExtra("UNIT_ID", currentItem.name)
            startActivity(this)
        }
    }

}
