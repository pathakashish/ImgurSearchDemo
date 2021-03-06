package com.ashish.imgursearchdemo.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ImageTest {
    @Test
    public void dataWithAnimatedFalseShouldBeSupported() {
        Image image = new Image("", "", "", false, "");
        assertTrue(image.isSupported());
    }

    @Test
    public void dataWithAnimatedTrueAndTypeImageGifShouldBeSupported() {
        Image image = new Image("", "", "image/gif", true, "");
        assertTrue(image.isSupported());
    }

    @Test
    public void dataWithAnimatedTrueAndTypeOtherThanImageGifShouldNotBeSupported() {
        Image image = new Image("", "", "video/mp4", true, "");
        assertFalse(image.isSupported());
    }

    @Test
    public void hasTitleReturnsFalseForNullTitle() {
        Image image = new Image("", null, "video/mp4", true, "");
        assertFalse(image.isSupported());
    }

    @Test
    public void hasTitleReturnsFalseForEmptyTitle() {
        Image image = new Image("", "", "video/mp4", true, "");
        assertFalse(image.isSupported());
    }

    @Test
    public void hasTitleReturnsTrueWhenTitleIsNeitherEmptyNotNull() {
        Image image = new Image("", "title", "video/mp4", true, "");
        assertFalse(image.isSupported());
    }
}
