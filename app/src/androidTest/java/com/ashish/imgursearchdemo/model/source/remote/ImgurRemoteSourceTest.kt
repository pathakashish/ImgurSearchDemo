package com.ashish.imgursearchdemo.model.source.remote

import com.ashish.imgursearchdemo.TestData
import com.ashish.imgursearchdemo.model.Image
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test

class ImgurRemoteSourceTest {

    @Test
    fun testImgurRemoteSourceReturnsAllSupportedImages() {
        val mockWebServer = MockWebServer()
        mockWebServer.dispatcher = TestData.getDispatcher()
        mockWebServer.start()
        val remoteSource = ImgurRemoteSource(TestData.getImgurApi(mockWebServer))
        val images = remoteSource.getImages("test").blockingGet()
        mockWebServer.shutdown()

        assertEquals(4, images.size)
        assertTrue(images.contains(TestData.getGifImage().copy(title = "Road trip photos")))
        assertTrue(images.contains(TestData.getStaticJpegImage().copy(title = "Road trip photos")))
        val dataWithStaticImage = TestData.getDataWithStaticJpeg()
        assertTrue(
            images.contains(
                Image(
                    dataWithStaticImage.id,
                    dataWithStaticImage.title,
                    dataWithStaticImage.type,
                    dataWithStaticImage.animated,
                    dataWithStaticImage.link!!
                )
            )
        )
        val dataWithAnimatedGif = TestData.getDataWithGif()
        assertTrue(
            images.contains(
                Image(
                    dataWithAnimatedGif.id,
                    dataWithAnimatedGif.title,
                    dataWithAnimatedGif.type,
                    dataWithAnimatedGif.animated,
                    dataWithAnimatedGif.link!!
                )
            )
        )
    }
}