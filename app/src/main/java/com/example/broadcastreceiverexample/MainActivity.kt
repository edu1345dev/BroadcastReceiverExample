package com.example.broadcastreceiverexample

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val btSend by lazy { findViewById<Button>(R.id.bt_send) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction("MY_ACTION")
        }

        val broadcastReceiver = MyBroadcastReceiver { type, state ->
            when (type) {
                MyBroadcastReceiver.INTERNET_STATE -> {
                    //change UI based on state
                }
                MyBroadcastReceiver.AIRPLANE_STATE -> {
                    //change UI based on state
                }
            }
        }

        registerReceiver(broadcastReceiver, filter)


        btSend.setOnClickListener {
            val intent = Intent()
            intent.action = "MY_ACTION"
            intent.putExtra("MAIN", "BUTTON_CLICK")
            sendBroadcast(intent)
        }
    }
}