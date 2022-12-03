package com.developerx.projecttwo

import android.util.Log
import javax.inject.Inject

class SimCard @Inject constructor(val serviceProvider: ServiceProvider) {
    init {
        Log.i("MYTAG", "SimCard Constructed...")
    }

    fun getConnection() {
        serviceProvider.getServiceProvider()
    }

}