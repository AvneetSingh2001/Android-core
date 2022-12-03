package com.developerx.projecttwo

import android.util.Log
import javax.inject.Inject

class NickelCadmiumBattery @Inject constructor(): Battery{
    override fun getPower() {
        Log.e("MYTAG", "Power from nickel battery")
    }
}