package com.ashish.imgursearchdemo.imgurapi

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ashish.imgursearchdemo.TestData
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImgurApiTest {

    @Test
    fun testThatRetrofitProvidesCorrectSearchResultObjectGivenRightJsonData() {
        val mockWebServer = MockWebServer()
        mockWebServer.dispatcher = TestData.getDispatcher()
        mockWebServer.start()

        val expectedSearchResult = TestData.getExpectedSearchResult()
        val apiService = TestData.getImgurApi(mockWebServer)
        val searchResult =
            apiService.search(0, "test").blockingGet()

        assertEquals(expectedSearchResult, searchResult)
        assertTrue(searchResult.data.contains(TestData.getDataWithGif()))
        assertTrue(searchResult.data.contains(TestData.getDataWithNonGif()))
        assertTrue(searchResult.data.contains(TestData.getDataWithStaticJpeg()))
        assertTrue(searchResult.data.contains(TestData.getDataWithImages()))

        mockWebServer.shutdown()
    }
}