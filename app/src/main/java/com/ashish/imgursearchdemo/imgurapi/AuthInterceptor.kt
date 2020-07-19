package com.ashish.imgursearchdemo.imgurapi

import okhttp3.Interceptor
import okhttp3.Response
import java.lang.StringBuilder
import javax.inject.Inject

/**
 * Intercepts the http request and adds the Authorization header.
 */
class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Authorization", getAuthHeader())
            .build()
        return chain.proceed(request)
    }

    private fun getAuthHeader(): String {
        val authSource = "1123374c5d6a768b95q0w0e8rat7ycu"
        val authBuilder = StringBuilder()
        authBuilder.append("Client-ID ")
        for (num in 0..30) {
            if (num % 2 != 0) {
                authBuilder.append(authSource[num])
            }
        }
        return authBuilder.toString()
    }
}