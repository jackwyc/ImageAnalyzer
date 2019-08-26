package com.jwyc.android.vision.api

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.security.Permissions

class PermissionUtil {

    fun requestPermission(
        activity: Activity, requestCode: Int, vararg permissions: String //vararg: 가변 인수
    ): Boolean {

        var granted = true
        val permissionNeeded = ArrayList<String>()

        permissions.forEach {
            val permissionCheck = ContextCompat.checkSelfPermission(activity, it)
            val hasPermission = permissionCheck == PackageManager.PERMISSION_GRANTED
            granted = granted and hasPermission
            if (!hasPermission) {
                permissionNeeded.add(it)
            }
        }
        if (granted) {
            return true
        } else {
            ActivityCompat.requestPermissions(
                activity, permissionNeeded.toTypedArray(), requestCode
            )
            return false
        }
    }

    fun permissionGranted(
        requestCode: Int, permissionCode: Int, grantResults: IntArray
    ): Boolean {
        return requestCode == permissionCode && grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
    }
}