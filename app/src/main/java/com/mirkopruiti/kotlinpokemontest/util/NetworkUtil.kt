package com.mirkopruiti.kotlinpokemontest.util

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {
    fun isConnectedToNetwork(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isConnected = false
        if (connectivityManager != null) {
            val activeNetwork = connectivityManager.activeNetworkInfo
            isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
        return isConnected
    }
}