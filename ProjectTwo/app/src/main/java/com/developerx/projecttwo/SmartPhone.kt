package com.developerx.projecttwo

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SmartPhone @Inject constructor(val battery : Battery, var simCard: SimCard, var memoryCard: MemoryCard) {
    init {
        battery.getPower()
        simCard.getConnection()
        memoryCard.getSpaceAvailability()
        Log.i("MYTAG", "SMARTPHONE constructed")
    }

    fun makeCallWithRecording() {
        Log.i("MYTAG", "Calling...")
    }
}