package com.ashish.imgursearchdemo.di

import android.content.Context
import com.ashish.imgursearchdemo.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationContextModule {

    @Provides
    @Singleton
    fun provideCacheFile(@ApplicationContext context: Context): File {
        return File(context.cacheDir, "okhttp_cache")
    }
}