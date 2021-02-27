package com.example.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class MyBroadcastReceiver(val stateCallback: (Int, Boolean) -> Unit) : BroadcastReceiver() {
    companion object {
        const val INTERNET_STATE = 0
        const val AIRPLANE_STATE = 1
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.toUri(Intent.URI_INTENT_SCHEME)
        handleIntent(intent, context)
    }

    private fun handleIntent(intent: Intent?, context: Context?) {
        intent?.let {
            when (intent.action) {
                ConnectivityManager.CONNECTIVITY_ACTION -> {
                    val state: Boolean = intent.extras?.getBoolean("noConnectivity") ?: true
                    this.stateCallback(INTERNET_STATE, !state)
                }

                Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                    val state = intent.extras?.getBoolean("state") ?: false
                    this.stateCallback(AIRPLANE_STATE, state)
                }

                "MY_ACTION" -> {
                    val text = intent.extras?.getString("MAIN")
                    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}