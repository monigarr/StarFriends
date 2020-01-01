package com.monigarr.swone.utilities

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtilities {
    private constructor()

    companion object {
        fun isNetworkConnectionAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //TODO: update this to modern best practices re android architecture, jetpack
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}