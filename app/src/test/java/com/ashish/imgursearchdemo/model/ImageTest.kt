package com.ashish.imgursearchdemo.model

import org.junit.Test

import org.junit.Assert.*

class ImageTest {
    @Test
    fun dataWithAnimatedFalseShouldBeSupported() {
        val image = Image("", "", "", false, "", listOf())
        assertTrue(image.isSupported())
    }

    @Test
    fun dataWithAnimatedTrueAndTypeImageGifShouldBeSupported() {
        val image = Image("", "", "image/gif", true, "", listOf())
        assertTrue(image.isSupported())
    }

    @Test
    fun dataWithAnimatedTrueAndTypeOtherThanImageGifShouldNotBeSupported() {
        val image = Image("", "", "video/mp4", true, "", listOf())
        assertFalse(image.isSupported())
    }
}