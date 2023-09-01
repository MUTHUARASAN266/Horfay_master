package com.example.horfay123.ui

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.horfay123.R
import com.example.horfay123.toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class Dashboard : AppCompatActivity() {
    val TAG="Dashboard"
    lateinit var navView : BottomNavigationView
    private lateinit var navController1234:NavController
    lateinit var view:View
    private lateinit var destinationListener: NavController.OnDestinationChangedListener
    lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        Log.e(TAG, "onCreate:")

        dialogBox()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        // Find reference to bottom navigation view
         navView = findViewById(R.id.bottom_navi)
        // Hook your navigation controller to bottom navigation view
        navView.setupWithNavController(navController)


        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_dashboard -> {
                    navController.navigate(R.id.home_dashboard)
                  //  navView.visibility=View.VISIBLE
                    true
                }
                R.id.notificationFragment -> {
                    navController.navigate(R.id.notificationFragment)
                    navView.visibility=View.VISIBLE
                    true
                }
                R.id.service_menu -> {
                    navView.visibility=View.VISIBLE
                  //  navController.navigate(R.id.notificationFragment)
                    true
                }
                R.id.chartFragmentScreen -> {
                    navView.visibility=View.VISIBLE
                    navController.navigate(R.id.chartFragmentScreen)
                   // navController.navigate(R.id.notificationFragment)
                    true
                }
                R.id.yourCartFragment->{
                    navView.visibility=View.VISIBLE
                    navController.navigate(R.id.yourCartFragment)
                    true
                }
                else -> false
            }
        }


        /*
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        */

     /*   toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = ""
        toolbar.setNavigationIcon(R.drawable.frame_menu)
        drawerLayout = findViewById(R.id.drawer)*/

      /*
        toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawer)
        val imageMenu:ImageView=findViewById(R.id.back_button)
        imageMenu.visibility=View.GONE

        initDrawer()
        //onBackPressedDispatcher.onBackPressed()
        findViewById<TextView>(R.id.logout_text).setOnClickListener {
            startActivity(Intent(this,SigninScreen::class.java))
            finish()
        }

       */

    }

    private fun dialogBox() {
        val builder = AlertDialog.Builder(this,R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.location_dialog,null)
        val  button = view.findViewById<TextView>(R.id.not_now_button)
        val  button1 = view.findViewById<MaterialButton>(R.id.ok_button)
        builder.setView(view)
        button.setOnClickListener {
            builder.dismiss()
        }
        button1.setOnClickListener {
            locationPermission()
            builder.dismiss()
        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }

    private fun locationPermission() {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            toast("PERMISSION_GRANTED")
        }else{
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
       if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
           ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION),100)
       }
        else{
           requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION),100)

       }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
            toast("PERMISSION_GRANTED")
        }else{
            toast("PERMISSION_NOT_GRANTED")
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initDrawer() {
        /*
        val drawerToggle = DuoDrawerToggle(this,drawerLayout,toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.setDrawerListener(drawerToggle)
        drawerToggle.syncState()
        */
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
////        startActivity(Intent(this,SigninScreen::class.java))
////            finish()
//    }
    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        currentFragment()
        Log.e(TAG, "onResume: ")
    }

    private fun currentFragment() {
        navController1234 = this.findNavController(R.id.fragmentContainerView)
        destinationListener = NavController.OnDestinationChangedListener { navController:NavController, destination: NavDestination, bundle:Bundle? ->
            when (destination.id) {
                R.id.home_dashboard->{
                    navView.visibility=View.GONE
                }
                R.id.allCategories -> {
                    navView.visibility=View.VISIBLE
                }
                R.id.notificationFragment -> {
                    navView.visibility=View.VISIBLE
                }
                R.id.chartFragmentScreen -> {
                    navView.visibility=View.VISIBLE
                }
                R.id.yourCartFragment->{
                    navView.visibility=View.VISIBLE

                }
                else -> {
                    navView.visibility=View.GONE
                }
            }
        }

        navController1234.addOnDestinationChangedListener(destinationListener)

    }

    override fun onPause() {
        super.onPause()
        navController1234.removeOnDestinationChangedListener(destinationListener)
        Log.e(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart: ", )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }
}