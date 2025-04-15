package com.example.domain.model

sealed class PermissionState{
    object PermissionGranted: PermissionState()
    object PermissionDenied: PermissionState()
    object PermissionNotRequested: PermissionState()
}