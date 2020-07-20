package com.ashish.imgursearchdemo.di

import android.content.Context
import com.ashish.imgursearchdemo.BuildConfig
import com.ashish.imgursearchdemo.imgurapi.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Singleton

const val CACHE_SIZE = 25L * 1024L * 1024L // Cache size in MB

@Module
@InstallIn(ApplicationComponent::class)
object OkHttpModule {

    @Provides
    @Singleton
    fun provideAuthInterceptorOkHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        cacheFile: File
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(Cache(cacheFile, CACHE_SIZE))
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            redactHeader("Authorization")
        }
    }
}