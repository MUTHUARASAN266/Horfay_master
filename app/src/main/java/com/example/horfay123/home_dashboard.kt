package com.example.horfay123

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horfay123.adapter.CleaningServicesAdapter
import com.example.horfay123.databinding.FragmentHomeDashboardBinding
import com.example.horfay123.model.CleaningSerData

class home_dashboard : Fragment(R.layout.fragment_home_dashboard) {
    lateinit var binding: FragmentHomeDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeDashboardBinding.inflate(inflater, container, false)

        binding.apply {

            servicesListRecyclerview.layoutManager=LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
            servicesListRecyclerview.hasFixedSize()
            val myValue= ArrayList<CleaningSerData>()
            myValue.add(CleaningSerData(R.drawable.image3,"Facial"))
            myValue.add(CleaningSerData(R.drawable.image4,"Pedicure"))
            myValue.add(CleaningSerData(R.drawable.image3,"Facial"))
            myValue.add(CleaningSerData(R.drawable.image4,"Pedicure"))
            servicesListRecyclerview.setItemViewCacheSize(myValue.size)
            val adapter=CleaningServicesAdapter(myValue)
            adapter.setHasStableIds(true)
            servicesListRecyclerview.adapter=adapter

            allCategories.setOnClickListener {
                findNavController().navigate(R.id.action_home_dashboard_to_allCategories)
            }
        }
        return binding.root

    }

}
