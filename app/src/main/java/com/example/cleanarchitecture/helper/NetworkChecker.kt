package com.example.cleanarchitecture.helper

import android.content.Context
import com.example.cleanarchitecture.utils.NetworkUtils

class NetworkChecker(private val context: Context) {
    fun isNetworkAvailable(): Boolean {
        return NetworkUtils.isInternetAvailable(context)
    }
}
