package com.example.horfay123

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.horfay123.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment(R.layout.fragment_notification) {
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
                fragmentManager?.popBackStack()

            }
        }

        return binding.root
    }
}