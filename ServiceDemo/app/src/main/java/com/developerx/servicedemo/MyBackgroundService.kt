package com.developerx.servicedemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyBackgroundService : Service() {

    init {
        Log.i(TAG, "Service instatiated")
    }

    companion object {
        const val TAG = "MYTAG"
        const val NAME = "NAME"
        const val MARKS = "MARKS"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.i(TAG, "Service Created")
        val name = intent?.getStringExtra(NAME)
        val marks = intent?.getIntExtra(MARKS, 0)
        Log.i(TAG, "Name is $name, Marks is $marks")
        return START_STICKY

    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onDestroy() {
        Log.i(TAG, "Service Destroyed")
        super.onDestroy()
    }

}