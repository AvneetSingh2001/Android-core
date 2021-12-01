package com.example.data_binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.data_binding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.submitButton.setOnClickListener {
            displayGreeting()
        }
    }

    fun displayGreeting() {
        binding.apply {
            textView.text = "Hello! " + inputName.text
        }
    }
}