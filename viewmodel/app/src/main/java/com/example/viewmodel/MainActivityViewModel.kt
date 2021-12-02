package com.example.viewmodel

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private var count = 0
    private var addValue = 0

    public fun getcount() : Int {
        return count
    }

    public fun setValue(value : Int)
    {
        addValue =  value
    }
    public fun getUpdatedCount() : Int {
        count += addValue
        return count
    }

}