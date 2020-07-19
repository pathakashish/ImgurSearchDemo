package com.ashish.imgursearchdemo.imgurapi

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Test


class AuthInterceptorTest {

    @Test
    fun interceptAddsAuthorizationHeader() {
        val expectedAuthHeaderValue = "Client-ID 137cda6b5008a7c"

        val mockWebServer = MockWebServer()
        mockWebServer.start()
        mockWebServer.enqueue(MockResponse())

        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(AuthInterceptor()).build()
        okHttpClient.newCall(Request.Builder().url(mockWebServer.url("/")).build()).execute()

        val request = mockWebServer.takeRequest()
        assertEquals(expectedAuthHeaderValue, request.getHeader("Authorization"))

        mockWebServer.shutdown()
    }
}