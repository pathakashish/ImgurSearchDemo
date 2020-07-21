package com.ashish.imgursearchdemo.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Singleton

/**
 * Add anything we need to get from Android framework here.
 */
@Module
@InstallIn(ApplicationComponent::class)
object ApplicationContextModule {

    @Provides
    @Singleton
    fun provideCacheFile(@ApplicationContext context: Context): File {
        return File(context.cacheDir, "okhttp_cache")
    }
}