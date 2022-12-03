package com.developerx.projecttwo

import android.util.Log
import javax.inject.Inject

class MemoryCard {

    init {
        Log.i("MYTAG", "Memory card Constructed...")
    }

    fun getSpaceAvailability() {
        Log.i("MYTAG", "Memory space is Available")
    }
}