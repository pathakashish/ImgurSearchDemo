package com.ashish.imgursearchdemo.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataTest {

    @Test
    public void dataWithAnimatedFalseShouldBeSupported() {
        Data data = new Data("", "", "", false, "", null);
        assertTrue(data.isSupported());
    }

    @Test
    public void dataWithAnimatedTrueAndTypeImageGifShouldBeSupported() {
        Data data = new Data("", "", "image/gif", true, "", null);
        assertTrue(data.isSupported());
    }

    @Test
    public void dataWithAnimatedTrueAndTypeOtherThanImageGifShouldNotBeSupported() {
        Data data = new Data("", "", "video/mp4", true, "", null);
        assertFalse(data.isSupported());
    }
}
