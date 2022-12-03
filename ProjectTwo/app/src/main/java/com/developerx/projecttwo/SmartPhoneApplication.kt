package com.developerx.projecttwo

import android.app.Application

class SmartPhoneApplication : Application() {

    lateinit var smartPhoneComponent: SmartPhoneComponent
    override fun onCreate() {
        smartPhoneComponent = initDagger()

        super.onCreate()

    }
    
    private fun initDagger(): SmartPhoneComponent  =
        DaggerSmartPhoneComponent
            .builder()
            .momoryCardModule(MomoryCardModule(128))
            .build()
}