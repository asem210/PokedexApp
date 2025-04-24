package com.example.cleanarchitecture.ui.components.scanner

import android.graphics.Rect
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.zxing.qrcode.encoder.QRCode
import kotlinx.coroutines.delay

@Composable
fun ScanCode(
    viewModel: ScannerViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onQrCodeDetected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    val barcode by viewModel.barcode.collectAsState()
    val qrCodeDetected by viewModel.qrCodeDetected.collectAsState()
    val boundingRect by viewModel.boundingRect.collectAsState()

    val cameraController = remember { LifecycleCameraController(context) }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { ctx ->
            PreviewView(ctx).apply {
                val options = BarcodeScannerOptions.Builder()
                    .setBarcodeFormats(
                        Barcode.FORMAT_QR_CODE,
                        Barcode.FORMAT_CODABAR,
                        Barcode.FORMAT_CODE_93,
                        Barcode.FORMAT_CODE_39,
                        Barcode.FORMAT_CODE_128,
                        Barcode.FORMAT_EAN_8,
                        Barcode.FORMAT_EAN_13,
                        Barcode.FORMAT_AZTEC
                    ).build()

                val barcodeScanner = BarcodeScanning.getClient(options)

                cameraController.setImageAnalysisAnalyzer(
                    ContextCompat.getMainExecutor(ctx),
                    MlKitAnalyzer(
                        listOf(barcodeScanner),
                        ImageAnalysis.COORDINATE_SYSTEM_VIEW_REFERENCED,
                        ContextCompat.getMainExecutor(ctx)
                    ) { result ->
                        val barcodeResults = result?.getValue(barcodeScanner)
                        if (!barcodeResults.isNullOrEmpty()) {
                            viewModel.onBarcodeDetected(
                                barcodeResults.first().rawValue,
                                barcodeResults.first().boundingBox
                            )
                        }
                    }
                )

                cameraController.bindToLifecycle(lifecycleOwner)
                this.controller = cameraController
            }
        }
    )

    if (qrCodeDetected) {
        LaunchedEffect(Unit) {
            delay(100)
            onQrCodeDetected(barcode ?: "")
            viewModel.resetDetection()
        }
        DrawRectangle(boundingRect)
    }
}
@Composable
fun DrawRectangle(rect: Rect?) {
    // Convert the Android Rect to a Compose Rect
    val composeRect = rect?.toComposeRect()

    // Draw the rectangle on a Canvas if the rect is not null
    composeRect?.let {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(it.left, it.top), // Set the top-left position
                size = Size(it.width, it.height), // Set the size of the rectangle
                style = Stroke(width = 5f) // Use a stroke style with a width of 5f
            )
        }
    }
}