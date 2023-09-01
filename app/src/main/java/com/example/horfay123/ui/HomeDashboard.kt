package com.example.horfay123.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horfay123.R
import com.example.horfay123.SharedPreferences
import com.example.horfay123.adapter.CleaningServicesAdapter
import com.example.horfay123.databinding.FragmentHomeDashboardBinding
import com.example.horfay123.model.CleaningSerData
import com.google.android.material.bottomsheet.BottomSheetDialog
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle

class HomeDashboard : Fragment(R.layout.fragment_home_dashboard) {
    private lateinit var drawerLayout: DuoDrawerLayout
    private lateinit var toolbar: Toolbar
    var TAG="HomeDashboard"

    lateinit var binding: FragmentHomeDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val locala=arguments?.getString("address123")
        Log.e(TAG, "onCreate: $locala")
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        // Inflate the layout for this fragment
        binding = FragmentHomeDashboardBinding.inflate(inflater, container, false)

        val local = savedInstanceState?.getString("address123")
        val locala=arguments?.getString("address123")
        Log.e(TAG, "onCreateView: $local$locala", )

        val sharedPreferences= SharedPreferences(context)
        sharedPreferences.loadData("myCurrentLocation")
        val data=  sharedPreferences.loadData("myCurrentLocation")
        if (data==null){
            binding.myCourentLocation.text="26A, KAZHUGAPULIKADU"
        }else{
            binding.myCourentLocation.text=sharedPreferences.loadData("myCurrentLocation")
            Log.e(TAG, "myCurrentLocation: ${sharedPreferences.loadData("myCurrentLocation")}", )
        }

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
                startActivity(Intent(activity, SubCategoriesScreen::class.java).putExtra("UNIT_ID","Apple orchard development"))
            }
            appleRepair.setOnClickListener {
                startActivity(Intent(activity, SubCategoriesScreen::class.java).putExtra("UNIT_ID","Appliance repair & service"))
            }
            architect.setOnClickListener {
                startActivity(Intent(activity, SubCategoriesScreen::class.java).putExtra("UNIT_ID","Architect"))
            }

            changeLocation.setOnClickListener {
                // BottomSheetDialog

//                val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(this@SelectedServiceScreen,R.style.CustomBottomSheetDialogTheme)
                val bottomSheetDialog = BottomSheetDialog(requireContext(),
                    R.style.CustomBottomSheetDialogTheme
                )
                val view=bottomSheetDialog.layoutInflater.inflate(R.layout.change_location_bottomscreen,null)

                view.findViewById<ConstraintLayout>(R.id.map_button).setOnClickListener {
                    startActivity(Intent(requireContext(), MapsActivity::class.java))
                }
                bottomSheetDialog.setContentView(view)
                bottomSheetDialog.show()

            }
            logoutText.setOnClickListener {
                startActivity(Intent(requireContext(),SigninScreen::class.java))


            }
            myProfile.setOnClickListener {
                startActivity(Intent(context,ProfileScreen::class.java))
            }

            var count=0
            supportBtn.setOnClickListener {
                if(count==0){
                    count++
                    layMuthu0.visibility=View.VISIBLE
                }else{
                    count=0
                    layMuthu0.visibility=View.GONE

                }
            }
            drawerClose.setOnClickListener {
                drawerLayout.closeDrawer()
            }

            aboutUsText.setOnClickListener {
                startActivity(Intent(context,AboutUsScreen::class.java))
            }
            tcnText.setOnClickListener {
                startActivity(Intent(context,TCnScreen::class.java))
            }
            contactUsText.setOnClickListener {
                startActivity(Intent(context,ContactUsScreen::class.java))
                Log.e(TAG, "onCreateView:ContactUsScreen", )
            }
            hello.setText(sharedPreferences.loadData("u_user_name"))
            number.setText(sharedPreferences.loadData("u_phoneNumber"))

        }
        binding.mutharsanKpk.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.home_dashboard -> {
                    findNavController().navigate(R.id.home_dashboard)
                    true
                }
                R.id.notificationFragment -> {
                    findNavController().navigate(R.id.notificationFragment)
                    binding.mutharsanKpk.visibility=View.GONE
                    true
                }
                R.id.service_menu -> {
                    //  navController.navigate(R.id.notificationFragment)
                    true
                }
                R.id.chartFragmentScreen -> {
                    findNavController().navigate(R.id.chartFragmentScreen)
                    binding.mutharsanKpk.visibility=View.GONE
                    true
                }
                R.id.yourCartFragment->{
                    findNavController().navigate(R.id.yourCartFragment)
                    binding.mutharsanKpk.visibility=View.GONE
                    true
                }
                else -> false
            }

        }



        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initDrawer() {
        val drawerToggle = DuoDrawerToggle(activity,drawerLayout,toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.setDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
    }

}
