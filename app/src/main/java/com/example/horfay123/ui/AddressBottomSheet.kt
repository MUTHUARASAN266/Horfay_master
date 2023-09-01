package com.example.horfay123.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.horfay123.R
import com.example.horfay123.SharedPreferences
import com.example.horfay123.databinding.FragmentAddressBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddressBottomSheet : BottomSheetDialogFragment() {
    var TAG = "AddressBottomSheet"
    lateinit var binding: FragmentAddressBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddressBottomSheetBinding.inflate(inflater, container, false)
        binding.apply {
            val sharedPreferences = SharedPreferences(context)
            addresTextView.text = sharedPreferences.loadData("myCurrentLocationAddress")
            cityTextviewAddBot.text = sharedPreferences.loadData("myCityAddressMapScreen")


            var click = 0
            homeBtnAddress.setOnClickListener {
                if (click == 0) {
                    click++
                    homeBtnAddress.apply {
                        setBackgroundResource(R.drawable.addres_round_btn)
                        backgroundTintList =
                            ContextCompat.getColorStateList(requireContext(), R.color.gray)
                    }
                } else {
                    click = 0
                    homeBtnAddress.apply {
                        setBackgroundResource(R.drawable.round_bg)
                        backgroundTintList =
                            ContextCompat.getColorStateList(requireContext(), R.color.card_back)
                    }
                }

            }
            otherBtnAddress.setOnClickListener {
                if (click == 0) {
                    click++
                    otherBtnAddress.apply {
                        otherEditText.visibility = View.VISIBLE
                        setBackgroundResource(R.drawable.addres_round_btn)
                        backgroundTintList =
                            ContextCompat.getColorStateList(requireContext(), R.color.gray)
                    }
                } else {
                    click = 0
                    otherBtnAddress.apply {
                        otherEditText.visibility = View.GONE
                        setBackgroundResource(R.drawable.round_bg)
                        backgroundTintList =
                            ContextCompat.getColorStateList(requireContext(), R.color.card_back)
                    }
                }

            }
            changeAddressBtn.setOnClickListener {
                startActivity(Intent(requireContext(),SearchLocationScreen::class.java))
            }
            saveAddressBtn.setOnClickListener {
                this@AddressBottomSheet.activity?.onBackPressedDispatcher?.onBackPressed()
                Log.e(TAG, "onCreateView: ${addresTextView.text}")
                sharedPreferences.saveData("AddressMapScreenData", addresTextView.text.toString())
            }


        }

        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.FullScreenBottomSheetDialog
    }
}