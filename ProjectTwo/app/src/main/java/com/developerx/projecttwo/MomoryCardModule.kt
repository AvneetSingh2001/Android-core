package com.developerx.projecttwo

import android.util.Log
import dagger.Module
import dagger.Provides

@Module
class MomoryCardModule(val memorySize: Int) {

    @Provides
    fun providesMemoryCard(): MemoryCard {
        Log.i("MYTAG", "Size of the memory is $memorySize")
        return MemoryCard()
    }
}