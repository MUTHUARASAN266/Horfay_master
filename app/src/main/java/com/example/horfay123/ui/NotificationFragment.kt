package com.example.horfay123.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.horfay123.R
import com.example.horfay123.databinding.FragmentNotificationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class NotificationFragment : Fragment(R.layout.fragment_notification) {
    var TAG="NotificationFragment"
    lateinit var binding:FragmentNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding=FragmentNotificationBinding.inflate(inflater, container, false)


        binding.apply {
            backButton.setOnClickListener {
               requireActivity().onBackPressedDispatcher.onBackPressed()
              //  findNavController().navigate(R.id.home_dashboard)
              //  parentFragmentManager.popBackStack()
                //fragmentManager?.popBackStack()

            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
    }
}