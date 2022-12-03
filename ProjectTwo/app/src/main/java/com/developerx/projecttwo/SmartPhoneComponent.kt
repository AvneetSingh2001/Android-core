package com.developerx.projecttwo

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MomoryCardModule::class, NCBatteryModule::class])
interface SmartPhoneComponent {

    fun inject(mainActivity: MainActivity)
}