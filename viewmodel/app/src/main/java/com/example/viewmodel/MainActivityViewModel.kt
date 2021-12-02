package com.example.viewmodel

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private var count = 0

    public fun getcount() : Int {
        return count
    }

    public fun getUpdatedCount() : Int {
        return ++count
    }

}