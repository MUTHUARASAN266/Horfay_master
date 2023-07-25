package com.example.horfay123

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.horfay123.databinding.FragmentChatScreenBinding

class ChatScreen : Fragment(R.layout.fragment_chat_screen) {
    lateinit var binding: FragmentChatScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatScreenBinding.inflate(inflater, container, false)
        binding.apply {
            backButton.setOnClickListener {
                 requireActivity().onBackPressedDispatcher.onBackPressed()
               // findNavController().navigate(R.id.home_dashboard)
                //  parentFragmentManager.popBackStack()
                //fragmentManager?.popBackStack()

            }
        }
       return binding.root
    }

}