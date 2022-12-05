package com.developerx.servicedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.developerx.servicedemo.MyBackgroundService.Companion.MARKS
import com.developerx.servicedemo.MyBackgroundService.Companion.NAME
import com.developerx.servicedemo.MyBackgroundService.Companion.TAG
import com.developerx.servicedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        val serviceIntent = Intent(this, MyBackgroundService::class.java)

        binding.apply {
            startBtn.setOnClickListener {
                Log.i(TAG, "Starting Service")
                startService(serviceIntent)
                serviceIntent.putExtra(NAME, "myname")
                serviceIntent.putExtra(MARKS, 45)
            }

            stopBtn.setOnClickListener {
                Log.i(TAG, "Stopping Service")
            }
        }
    }
}