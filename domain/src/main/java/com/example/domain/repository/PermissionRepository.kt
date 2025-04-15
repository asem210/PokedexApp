package com.example.domain.repository

import com.example.domain.model.PermissionState

interface PermissionRepository {
    fun checkCameraPermission(): PermissionState
    fun requestCameraPermission(): PermissionState
}