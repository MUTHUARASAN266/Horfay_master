package com.example.horfay123

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horfay123.adapter.CleaningServicesAdapter
import com.example.horfay123.databinding.FragmentHomeDashboardBinding
import com.example.horfay123.model.CleaningSerData
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle

class HomeDashboard : Fragment(R.layout.fragment_home_dashboard) {
    private lateinit var drawerLayout: DuoDrawerLayout
    private lateinit var toolbar: Toolbar

    lateinit var binding: FragmentHomeDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeDashboardBinding.inflate(inflater, container, false)

        toolbar = binding.toolBarDashboard
     //   (activity as AppCompatActivity).setSupportActionBar(toolbar)
       // setSupportActionBar(toolbar)
       // toolbar.setNavigationIcon(R.drawable.frame_menu)
        drawerLayout = binding.drawerDashBoard
//        drawerLayout = findViewById(R.id.drawer)

        initDrawer()

        binding.apply {

            servicesListRecyclerview.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            servicesListRecyclerview.hasFixedSize()
            val myValue = ArrayList<CleaningSerData>()
            myValue.add(CleaningSerData(R.drawable.image3, "Facial"))
            myValue.add(CleaningSerData(R.drawable.image4, "Pedicure"))
            myValue.add(CleaningSerData(R.drawable.image3, "Facial"))
            myValue.add(CleaningSerData(R.drawable.image4, "Pedicure"))
            servicesListRecyclerview.setItemViewCacheSize(myValue.size)
            val adapter = CleaningServicesAdapter(myValue)
            adapter.setHasStableIds(true)
            servicesListRecyclerview.adapter = adapter

            allCategories.setOnClickListener {
                findNavController().navigate(R.id.action_home_dashboard_to_allCategories)
            }
            appleOrchard.setOnClickListener {
                startActivity(Intent(activity,SubCategoriesScreen::class.java).putExtra("UNIT_ID","Apple orchard development"))
            }
            appleRepair.setOnClickListener {
                startActivity(Intent(activity,SubCategoriesScreen::class.java).putExtra("UNIT_ID","Appliance repair & service"))
            }
            architect.setOnClickListener {
                startActivity(Intent(activity,SubCategoriesScreen::class.java).putExtra("UNIT_ID","Architect"))
            }
        }
        return binding.root

    }

    private fun initDrawer() {
        val drawerToggle = DuoDrawerToggle(activity,drawerLayout,toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.setDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

}
