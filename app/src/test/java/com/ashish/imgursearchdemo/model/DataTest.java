package com.ashish.imgursearchdemo.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void supportedImagesWithDataReturnsImageListWithOneImageObjectWithIdTitleTypeAnimatedAndLinkFieldsCopiedWhenImagesIsNull() {
        Data data = new Data("1", "1", "image/jpeg", false, "https://test.jpg", null);
        List<Image> images = data.supportedImagesWithData();
        assertEquals(1, images.size());
        assertEquals(new Image("1", "1", "image/jpeg", false, "https://test.jpg"), images.get(0));
    }

    @Test
    public void supportedImagesWithDataReturnsImageListWithOnlySupportedImgesImagesIsNonNull() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("1", "1", "image/jpeg", false, "https://test.jpg"));
        images.add(new Image("1", "1", "image/gif", true, "https://test.jpg"));
        images.add(new Image("1", "1", "video/mp4", true, "https://test.jpg"));
        Data data = new Data("1", "1", null, false, "", images);
        List<Image> supportedImages = data.supportedImagesWithData();
        assertEquals(2, supportedImages.size());
        assertEquals(new Image("1", "1", "image/jpeg", false, "https://test.jpg"), supportedImages.get(0));
        assertEquals(new Image("1", "1", "image/gif", true, "https://test.jpg"), supportedImages.get(1));
    }

    @Test
    public void supportedImagesWithDataReturnsImageListWithOnlySupportedImgesImagesIsNonNullChangesTitleOnlyIfImagesDoesNotHaveTitle() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("1", "", "image/jpeg", false, "https://test.jpg"));
        images.add(new Image("1", null, "image/gif", true, "https://test.jpg"));
        images.add(new Image("1", "1", "video/mp4", true, "https://test.jpg"));
        images.add(new Image("1", "title", "image/gif", true, "https://test.jpg"));
        Data data = new Data("1", "2", null, false, "", images);
        List<Image> supportedImages = data.supportedImagesWithData();
        assertEquals(3, supportedImages.size());
        assertEquals(new Image("1", "2", "image/jpeg", false, "https://test.jpg"), supportedImages.get(0));
        assertEquals(new Image("1", "2", "image/gif", true, "https://test.jpg"), supportedImages.get(1));
        assertEquals(new Image("1", "title", "image/gif", true, "https://test.jpg"), supportedImages.get(2));
    }
}
