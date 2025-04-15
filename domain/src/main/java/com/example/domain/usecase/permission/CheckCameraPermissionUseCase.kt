package com.example.domain.usecase.permission

import com.example.domain.model.PermissionState
import com.example.domain.repository.PermissionRepository

class CheckCameraPermissionUseCase (private val permissionRepository: PermissionRepository) {
    operator fun invoke():PermissionState{
        return permissionRepository.checkCameraPermission()
    }
}