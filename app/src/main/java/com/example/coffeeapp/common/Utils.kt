package com.example.coffeeapp.common

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.coffeeapp.R
import com.google.android.material.snackbar.Snackbar

object Utils {

    fun isLocationPermissionGranted(context: Context) =
        ActivityCompat.checkSelfPermission(
            context, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    fun isGPSEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    fun showSnackBar(view: View, message: String, callback: () -> Unit) {
        Snackbar.make(
            view, message, Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.ok) {
            callback()
        }.show()
    }

    fun showToast(context: Context, message: String?) {
        Toast.makeText(context, message ?:context.getString(R.string.unknown_error), Toast.LENGTH_LONG).show()
    }
}