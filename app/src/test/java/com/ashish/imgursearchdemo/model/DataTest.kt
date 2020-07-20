package com.ashish.imgursearchdemo.model

import org.junit.Assert.*
import org.junit.Test

class DataTest {
    @Test
    fun dataWithAnimatedFalseShouldBeSupported() {
        val data = Data("", "", "", false, "", null)
        assertTrue(data.isSupported())
    }

    @Test
    fun dataWithAnimatedTrueAndTypeImageGifShouldBeSupported() {
        val data = Data("", "", "image/gif", true, "", null)
        assertTrue(data.isSupported())
    }

    @Test
    fun dataWithAnimatedTrueAndTypeOtherThanImageGifShouldNotBeSupported() {
        val data = Data("", "", "video/mp4", true, "", null)
        assertFalse(data.isSupported())
    }
}