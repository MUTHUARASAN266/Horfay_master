package com.example.horfay123.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.horfay123.R
import com.example.horfay123.databinding.FragmentAboutUsServiceBinding

class AboutUsServiceFragment : Fragment(R.layout.fragment_about_us_service) {
    lateinit var binding: FragmentAboutUsServiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAboutUsServiceBinding.inflate(inflater, container, false)
        return binding.root
    }

}