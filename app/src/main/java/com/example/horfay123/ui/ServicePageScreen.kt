package com.example.horfay123.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horfay123.R
import com.example.horfay123.SharedPreferences
import com.example.horfay123.adapter.AddressBottomAdapter
import com.example.horfay123.adapter.SelectedServiceAdapter
import com.example.horfay123.databinding.ActivityServicePageScreenBinding
import com.example.horfay123.`interface`.AddressBottomSheetListener
import com.example.horfay123.model.AddressItemBot
import com.example.horfay123.model.AddressItemBotFull
import com.example.horfay123.model.VendorCartItemData
import com.example.horfay123.snack
import com.example.horfay123.toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class ServicePageScreen : AppCompatActivity() {
    lateinit var binding: ActivityServicePageScreenBinding
    val TAG = "ServicePageScreen"
    private val dataList: MutableList<AddressItemBot> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServicePageScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val serviceData: ArrayList<VendorCartItemData>? =
            intent.getParcelableArrayListExtra("ServicePageScreen_Data")

        binding.apply {
            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            selectedServiceListRecyclerview.layoutManager =
                LinearLayoutManager(this@ServicePageScreen)
            selectedServiceListRecyclerview.hasFixedSize()

            if (serviceData != null) {
                val adapter = SelectedServiceAdapter(serviceData)
                selectedServiceListRecyclerview.adapter = adapter
            } else {
                Log.e(TAG, "onCreate: cancelefahfaowhefoawe")
            }
            textviewViewService.setOnClickListener {
                startActivity(Intent(this@ServicePageScreen, VendorProfileScreen::class.java))
            }
            buttonAddCart.setOnClickListener {
//                startActivity(Intent(this@ServicePageScreen,YourCartScreen::class.java))
                val bottomSheetFragment = FullScreenBottomSheet()
                bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
            }
            buttonBookNow.setOnClickListener {
                val bottomSheetDialog =
                    BottomSheetDialog(this@ServicePageScreen, R.style.CustomBottomSheetDialogTheme)
                val view =
                    bottomSheetDialog.layoutInflater.inflate(R.layout.address_bottomseet, null)

//                val behavior = BottomSheetBehavior.from(view)
//                behavior.isFitToContents = false
//                behavior.halfExpandedRatio = 0.6f

                // bottomSheet RecycleView
                val botRecyclerView =
                    view.findViewById<RecyclerView>(R.id.address_bottomsheet_recycleview)
                val add = view.findViewById<TextView>(R.id.add_button_text)
                val btnProcess=view.findViewById<Button>(R.id.btn_proced_location)
                val data = ArrayList<AddressItemBot>()
                var addressBottomAdapter = AddressBottomAdapter(dataList)

                btnProcess.setOnClickListener {
                    val dataBottomSheetDialog=DateAndTimeBottomSheet()
                    dataBottomSheetDialog.show(supportFragmentManager,dataBottomSheetDialog.tag)
                    bottomSheetDialog.dismiss()
                }

                add.setOnClickListener {
//                    toast("data added")
//                    it.snack("data added", 1500)
//                    Log.e(TAG, "onCreate: data added")
//                    data.add(
//                        AddressItemBot(
//                            "Muthu Home",
//                            " 123, kazhugapulikadu, Pattukkottai - Peravurani Rd, 614602"
//                        )
//                    )
//                    addressBottomAdapter.notifyDataSetChanged()
                    startActivity(Intent(this@ServicePageScreen,AddressMapScreen::class.java))

                }
                botRecyclerView.layoutManager = LinearLayoutManager(this@ServicePageScreen)
                botRecyclerView.hasFixedSize()
                val sharedPreferences=SharedPreferences(this@ServicePageScreen)
                // adding data in recyclerview adapter
//                data.add(
//                    AddressItemBot(
//                        "Home",
//                        "89, Bhel Nagar, Piplani, Ayodhya Bypass, Bhopal,\n Madhya Pradesh 462022, India "
//                    )
//                )
//                data.add(
//                    AddressItemBot(
//                        "Muthu Home",
//                        " 123, kazhugapulikadu, Pattukkottai - Peravurani Rd, 614602"
//                    )
//                )
//                data.add(
//                    AddressItemBot(
//                        "office",
//                        " 124, kazhugapulikadu, Pattukkottai - Peravurani Rd, 614602"
//                    )
//                )

              //  data.add(AddressItemBot("Home12",sharedPreferences.loadData("AddressMapScreenData")))
                val newItem = AddressItemBot("New Title", sharedPreferences.loadData("AddressMapScreenData"))
                addressBottomAdapter.addNewItem(newItem)
                Log.e(TAG, "onCreate->dataList: $newItem" )
                Log.e(TAG, "onCreate->dataList: ${dataList.size} " )
                addressBottomAdapter.notifyItemInserted(dataList.size - 1)

             //   addressBottomAdapter = AddressBottomAdapter(data)
                addressBottomAdapter.setHasStableIds(true)

//                addressBottomAdapter.setOnClickListener(object :AddressBottomSheetListener{
//                    override fun onItemClick(
//                        position: Int,
//                        addressItemBot: AddressItemBot,
//                        isSelect: Boolean
//                    ) {
//                        Log.e(TAG, "onItemClick: value=> position-$position isSelect-> $isSelect")
//                    }
//                })
                addressBottomAdapter.setOnClickListener1(object : AddressBottomSheetListener {
                    override fun onItemClick(
                        position: Int,
                        addressItemBot: AddressItemBot,
                        isSelect: Boolean
                    ) {
                        Log.e(
                            TAG,
                            "onItemClick: value=> position-$position isSelect-> $isSelect, addressItemBot-> $addressItemBot"
                        )

                    }

                    override fun onItemDelete(
                        position: Int,
                        addressItemBot: AddressItemBot,
                        isSelect: Boolean
                    ) {
                        data.removeAt(position)
                        addressBottomAdapter.notifyDataSetChanged()
                        Log.e(
                            TAG,
                            "onItemClick: value=> position-$position isSelect-> $isSelect, addressItemBot-> $addressItemBot"
                        )
                    }
                })
                botRecyclerView.adapter = addressBottomAdapter

                // bottomSheet function
//                bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
//                bottomSheetDialog.behavior.expandedOffset=700
                bottomSheetDialog.behavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO

                bottomSheetDialog.setCancelable(true)
                bottomSheetDialog.setContentView(view)
                bottomSheetDialog.show()
            }
        }
    }
}