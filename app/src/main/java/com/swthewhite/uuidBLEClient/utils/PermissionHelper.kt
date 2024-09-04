package com.swthewhite.uuidBLEClient.utils

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat

// Not class, But object => for singleton
// Reason: Because the PermissionsHelper class does not have any state, it is a good candidate for an object.
object PermissionsHelper {
    private val requiredPermissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        arrayOf(
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_ADVERTISE
        )
    } else {
        arrayOf(
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.BLUETOOTH
        )
    }

    fun hasAllPermissions(activity: ComponentActivity): Boolean {
        return requiredPermissions.all {
            ActivityCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun requestPermissions(activity: ComponentActivity) {
        ActivityCompat.requestPermissions(activity, requiredPermissions, 1)
    }
}
