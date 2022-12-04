package com.developerx.projectthree

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class MyCalcTest {

    private lateinit var myCalc: MyCalc

    @Before
    fun setUp() {
        myCalc = MyCalc()
    }

    @Test
    fun calculateCircumference_radiusGiven_returnsCorrectResult() {
        myCalc = MyCalc()
        val result =  myCalc.calculateCircumference(2.1)
        assertThat(result).isEqualTo(13.188)

    }

    @Test
    fun calculateCircumeference_zeroRadius_returnZero() {
        val result = myCalc.calculateCircumference(0.0)
        assertThat(result).isEqualTo(0.0)
    }
}