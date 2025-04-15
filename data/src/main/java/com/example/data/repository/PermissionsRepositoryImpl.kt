package com.example.data.repository

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.domain.model.PermissionState
import com.example.domain.repository.PermissionRepository

class PermissionsRepositoryImpl (private val context: Context):PermissionRepository {
    override fun checkCameraPermission(): PermissionState {
        return if(ActivityCompat.checkSelfPermission(context,android.Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){
            PermissionState.PermissionGranted
        }else{
            PermissionState.PermissionDenied
        }
    }

    override fun requestCameraPermission(): PermissionState {
        return PermissionState.PermissionNotRequested
    }
}