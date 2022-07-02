package com.example.coffeeapp.common

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar

fun Context.hasPermission(permission: String) = ActivityCompat.checkSelfPermission(
    this, permission
) == PackageManager.PERMISSION_GRANTED

fun Activity.requestPermissionWithRationale(
    permission: String,
    requestCode: Int,
    snackbar: Snackbar
){
    val provideRationale = shouldShowRequestPermissionRationale(permission)
    if(provideRationale){
        snackbar.show()
    }else{
        ActivityCompat.requestPermissions(
            this,
            arrayOf(permission),
            requestCode
        )
    }
}