package mx.openpay.challenge.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.GeoPoint
import dagger.hilt.android.AndroidEntryPoint
import mx.openpay.challenge.R
import mx.openpay.challenge.data.ChallengeConstant
import mx.openpay.challenge.data.model.entity.Place
import mx.openpay.challenge.tools.DataState
import mx.openpay.challenge.tools.Utils

@AndroidEntryPoint
class MapsFragment : Fragment() {

    private lateinit var googleMap: GoogleMap
    private val mapsViewModel: MapsViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap
        mapsViewModel.getAllPlace()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
        mapFragment?.getMapAsync(callback)
        observers()
        getLastKnownLocation()
        initCountDownTimer()
    }

    private fun getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                mapsViewModel.savePlace(
                    Place(coordinate = GeoPoint(location.latitude, location.longitude))
                )
                animateCamera(location.latitude, location.longitude)
            }
        }
    }

    private fun animateCamera(
        latitude: Double, longitude: Double
    ) {
        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    latitude, longitude
                ), 20.0f
            )
        )
    }

    private fun observers() {
        mapsViewModel.savePlaceState.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    Log.d("savePlaceState", getString(R.string.text_saved_success))
                    Utils.showNotification(
                        requireActivity(),
                        getString(R.string.text_saved_success)
                    )
                }
                is DataState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.text_error),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                else -> Unit
            }
        }

        mapsViewModel.getPlaceState.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    dataState.data.forEach { place ->
                        googleMap.addMarker(
                            MarkerOptions().position(
                                LatLng(
                                    place.coordinate?.latitude ?: 0.0,
                                    place.coordinate?.longitude ?: 0.0
                                )
                            ).title(
                                place.date.toDate().toString()
                            )
                        )
                    }
                }
                is DataState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.text_error),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                else -> Unit
            }
        }
    }

    private fun initCountDownTimer() {
        mapsViewModel.getAllPlace()
        object : CountDownTimer(
            ChallengeConstant.MILLISECONDS_LOCATIONS, ChallengeConstant.MILLISECONDS_INTERVAL
        ) {
            override fun onTick(millisUntilFinished: Long) = Unit
            override fun onFinish() {
                initCountDownTimer()
            }
        }.start()
    }

}
