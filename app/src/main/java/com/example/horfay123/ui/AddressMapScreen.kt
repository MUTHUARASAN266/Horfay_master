package com.example.horfay123.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.horfay123.R
import com.example.horfay123.SharedPreferences
import com.example.horfay123.databinding.ActivityAddressMapScreenBinding
import com.example.horfay123.toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Locale


class AddressMapScreen : AppCompatActivity(),OnMapReadyCallback {
    lateinit var binding: ActivityAddressMapScreenBinding
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var mMap: GoogleMap
    val TAG="AddressMapScreen"
    var currentLocation: LatLng = LatLng(10.413313389150035,  79.27949046606943)
    lateinit var geocoder: Geocoder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddressMapScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        binding.apply {
            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            val mapFragment=supportFragmentManager.findFragmentById(R.id.address_map_screen) as SupportMapFragment
            mapFragment.getMapAsync(this@AddressMapScreen)
            mFusedLocationClient=LocationServices.getFusedLocationProviderClient(this@AddressMapScreen)

            myCurrentLocBtn.setOnClickListener {
                getMyCurrentLocation()
            }
        }
    }

    private fun getMyCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->

            val bottomSheetFragment = AddressBottomSheet()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
            val location: Location? = task.result
            if (location == null) {
                requestNewLocationData()
            } else {
                currentLocation = LatLng(location.latitude, location.longitude)
                mMap.clear()
                mMap.addMarker(
                    MarkerOptions().position(currentLocation).title("Move pin to your exact location")
                        .icon(generateBitmapDescriptorFromRes(this, R.drawable.marker_map12)))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16F))
                getAddress(location.latitude, location.longitude)
            }
        }
    }
    private fun generateBitmapDescriptorFromRes(context: Context, resId: Int): BitmapDescriptor {
        val drawable = ContextCompat.getDrawable(context, resId)
        drawable!!.setBounds(
            0,
            0,
            drawable.intrinsicWidth,
            drawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
    private fun requestNewLocationData() {
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 0)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(0)
            .setMaxUpdateDelayMillis(1)
            .build()
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mFusedLocationClient.requestLocationUpdates(
            locationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location? = locationResult.lastLocation
            currentLocation = LatLng(mLastLocation!!.latitude, mLastLocation.longitude)
        }
    }
    private fun getAddress(latitude: Double, longitude: Double) {
        geocoder = Geocoder(this, Locale.getDefault())
        val addresses: List<Address>? = geocoder.getFromLocation(latitude, longitude , 1) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        // 10.413313389150035, 79.27949046606943
        // 51.69026693985225, -0.41756896364015167 UK
        // 27.175297436920296, 78.04243185161019 TajMahal

        try {
            /* if (addresses?.isEmpty()==true){
                 toast("its sea please click ground")
             }*/
            val address: String = addresses?.get(0)!!.getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            val sendAddress="${addresses[0].locality}, ${addresses[0].adminArea}"

            // data passing
            val bundle = Bundle()
            bundle.putString("address123", sendAddress)

            val fragment = HomeDashboard()
            fragment.arguments = bundle
            val sharedPreferences= SharedPreferences(this)
            sharedPreferences.saveData("myCurrentLocation", sendAddress)
            sharedPreferences.saveData("myCurrentLocationAddress", address)
            sharedPreferences.saveData("myCityAddressMapScreen", addresses[0].locality)

            val city: String? = addresses[0].locality
            val state: String? = addresses[0].adminArea
            val country: String? = addresses[0].countryName
            val postalCode: String? = addresses[0].postalCode
            val knownName: String? = addresses[0].featureName

            Log.e(TAG, "getAddress: ${"$city $state $country $postalCode $knownName ${addresses[0].subLocality}"}", )
        } catch (e:Exception){
            toast("this is sea. can you please click land side")
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getMyCurrentLocation()

        mMap.setOnMapClickListener { latlng -> // Clears the previously touched position
            mMap.clear();
            // Animating to the touched position
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latlng));

            val location = LatLng(latlng.latitude, latlng.longitude)
            mMap.addMarker(MarkerOptions().position(location).icon(generateBitmapDescriptorFromRes(this,
                R.drawable.marker_map12
            )))

            getAddress(latlng.latitude, latlng.longitude)

            val bottomSheetFragment = AddressBottomSheet()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
        //getAddress(latitude,longitude)
        googleMap.uiSettings.isZoomGesturesEnabled = true;
        googleMap.uiSettings.isRotateGesturesEnabled = true;
    }
}