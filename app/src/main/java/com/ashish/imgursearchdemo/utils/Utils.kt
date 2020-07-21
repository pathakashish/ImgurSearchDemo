package com.ashish.imgursearchdemo.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Check if device is connected to any network.
 */
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo
        .isConnected
}