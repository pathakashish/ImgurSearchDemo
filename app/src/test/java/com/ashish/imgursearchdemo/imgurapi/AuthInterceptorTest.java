package com.ashish.imgursearchdemo.imgurapi;

import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertEquals;

public class AuthInterceptorTest {

    @Test
    public void interceptAddsAuthorizationHeader() throws Exception {
        String expectedAuthHeaderValue = "Client-ID 137cda6b5008a7c";

        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockWebServer.enqueue(new MockResponse());

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new AuthInterceptor()).build();
        okHttpClient.newCall(new Request.Builder().url(mockWebServer.url("/")).build()).execute();

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals(expectedAuthHeaderValue, request.getHeader("Authorization"));

        mockWebServer.shutdown();
    }
}