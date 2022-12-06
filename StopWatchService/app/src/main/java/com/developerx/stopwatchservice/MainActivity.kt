package com.developerx.stopwatchservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developerx.stopwatchservice.StopWatchService.Companion.CURRENT_TIME
import com.developerx.stopwatchservice.StopWatchService.Companion.UPDATED_TIME
import com.developerx.stopwatchservice.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var isStarted = false

    private lateinit var serviceIntent : Intent
    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            startButton.setOnClickListener {
                startOrStop()
            }

            resetButton.setOnClickListener {
                reset()
            }
        }

        serviceIntent = Intent(applicationContext, StopWatchService::class.java)
        registerReceiver(updatetime, IntentFilter(StopWatchService.UPDATED_TIME))
    }

    private fun startOrStop() {
        if(isStarted)
            stop()
        else
            start()
    }

    private fun start() {
        isStarted = true
        serviceIntent.putExtra(CURRENT_TIME, time)
        startService(serviceIntent)
        binding.startButton.text = "STOP"
    }

    private fun reset() {
        stop()
        time = 0.0
        binding.timerTextView.text = getformattedTime(time)
    }

    private fun stop() {
        isStarted = false
        stopService(serviceIntent)
        binding.startButton.text = "START"
    }

    private val updatetime : BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(StopWatchService.CURRENT_TIME, 0.0)
            binding.timerTextView.text = getformattedTime(time)
        }
    }

    private fun  getformattedTime(time: Double): String {
        val timeInt = time.roundToInt()
        val hours = timeInt % 86400 / 3600
        val minutes = timeInt % 86400 % 3600 / 60
        val seconds = timeInt % 86400 % 3600 % 60


        return String.format("%02d:%02d:%02d",hours, minutes, seconds)
    }
}