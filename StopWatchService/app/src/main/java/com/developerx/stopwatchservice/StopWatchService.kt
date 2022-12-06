package com.developerx.stopwatchservice

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import java.util.Objects
import java.util.Timer
import java.util.TimerTask

class StopWatchService : Service(){
    override fun onBind(p0: Intent?): IBinder? = null
     val timer = Timer()

    companion object {
        const val CURRENT_TIME = "current_time"
        const val UPDATED_TIME = "updated_time"
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val time = intent.getDoubleExtra(CURRENT_TIME, 0.0)
        timer.scheduleAtFixedRate(StopWatchTimerTask(time),0,1)

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

    private inner class StopWatchTimerTask(private var time : Double) : TimerTask() {
        override fun run() {
            val intent = Intent(UPDATED_TIME)
            time++
            intent.putExtra(CURRENT_TIME, time)
            sendBroadcast(intent)
        }

    }


}