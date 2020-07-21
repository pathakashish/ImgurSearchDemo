package com.ashish.imgursearchdemo.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ImageTest {
    @Test
    public void dataWithAnimatedFalseShouldBeSupported() {
        Image image = new Image("", "", "", false, "", new ArrayList<>());
        assertTrue(image.isSupported());
    }

    @Test
    public void dataWithAnimatedTrueAndTypeImageGifShouldBeSupported() {
        Image image = new Image("", "", "image/gif", true, "", new ArrayList<>());
        assertTrue(image.isSupported());
    }

    @Test
    public void dataWithAnimatedTrueAndTypeOtherThanImageGifShouldNotBeSupported() {
        Image image = new Image("", "", "video/mp4", true, "", new ArrayList<>());
        assertFalse(image.isSupported());
    }
}
