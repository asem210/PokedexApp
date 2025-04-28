package com.example.cleanarchitecture.ui.components.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.helper.NetworkChecker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NetworkStatusViewModel(
    private val networkChecker: NetworkChecker
) : ViewModel() {

    private val _isConnected = MutableStateFlow(true)
    val isConnected: StateFlow<Boolean> = _isConnected

    init {
        monitorNetwork()
    }

    private fun monitorNetwork() {
        viewModelScope.launch {
            _isConnected.value = networkChecker.isNetworkAvailable()
        }
    }

    fun refreshConnectionStatus() {
        viewModelScope.launch {
            _isConnected.value = networkChecker.isNetworkAvailable()
        }
    }
}