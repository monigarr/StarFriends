package com.monigarr.swone.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.monigarr.swone.utilities.NetworkUtilities

class ConnectivityReceiver(private var connectivityReceiverListener: ConnectivityReceiverListener? = null) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        connectivityReceiverListener?.onNetworkConnectionChanged(
            NetworkUtilities.isNetworkConnectionAvailable(
                context
            )
        )
    }

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {
        var connectivityReceiverListener: ConnectivityReceiverListener? = null
    }
}