package com.example.horfay123.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.horfay123.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Dashboard : AppCompatActivity() {
    val TAG="Dashboard"
    lateinit var bottomNav : BottomNavigationView
    lateinit var view:View
    lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        Log.e(TAG, "onCreate:")


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController: NavController = navHostFragment.navController

       // val navController = this.findNavController(R.id.fragmentContainerView)


        // Find reference to bottom navigation view
        val navView: BottomNavigationView = findViewById(R.id.bottom_navi)
        // Hook your navigation controller to bottom navigation view
        navView.setupWithNavController(navController)

        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_dashboard -> {
                    navController.navigate(R.id.home_dashboard)
                    true
                }
                R.id.notificationFragment -> {
                    navController.navigate(R.id.notificationFragment)
                    true
                }
                R.id.service_menu -> {
                  //  navController.navigate(R.id.notificationFragment)
                    true
                }
                R.id.message_menu -> {
                   // navController.navigate(R.id.notificationFragment)
                    true
                }
                else -> false
            }
        }


        /*
        bottomNav.setOnItemSelectedListener { menuItem->
            when (menuItem.itemId) {
                R.id.home_menu -> {

                    true
                }
                R.id.notification_menu -> {

                    menuItem.actionView?.findNavController()?.navigate(R.id.action_home_dashboard_to_notificationFragment)
//                    Navigation.findNavController(view).navigate(R.id.action_home_dashboard_to_notificationFragment);
//                    findNavController().navigate(R.id.action_home_dashboard_to_notificationFragment)
                    true
                }
                R.id.service_menu -> {

                    true
                }
                R.id.message_menu -> {
                    true
                }
                else -> {
                    false
                }
            }
        }
        */


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

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,SigninScreen::class.java))
            finish()
    }
    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
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