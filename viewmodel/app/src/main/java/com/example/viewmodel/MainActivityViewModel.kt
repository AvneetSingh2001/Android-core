package com.example.viewmodel

import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal : Int) : ViewModel() {
    private var count = 0

    init{
        count = startingTotal
    }

    fun getcount() : Int {
        return count
    }

    fun getUpdatedCount(addValue : Int) : Int {
        count += addValue
        return count
    }

}