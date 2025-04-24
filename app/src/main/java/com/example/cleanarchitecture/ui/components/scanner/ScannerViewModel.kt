package com.example.cleanarchitecture.ui.components.scanner

import android.graphics.Rect
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ScannerViewModel : ViewModel() {

    private val _barcode = MutableStateFlow<String?>(null)
    val barcode: StateFlow<String?> = _barcode

    private val _boundingRect = MutableStateFlow<Rect?>(null)
    val boundingRect: StateFlow<Rect?> = _boundingRect

    private val _qrCodeDetected = MutableStateFlow(false)
    val qrCodeDetected: StateFlow<Boolean> = _qrCodeDetected

    fun onBarcodeDetected(code: String?, rect: Rect?) {
        _barcode.value = code
        _boundingRect.value = rect
        _qrCodeDetected.value = true
    }

    fun resetDetection() {
        _qrCodeDetected.value = false
    }
}