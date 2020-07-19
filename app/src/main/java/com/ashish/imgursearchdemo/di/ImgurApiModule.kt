package com.ashish.imgursearchdemo.di

import com.ashish.imgursearchdemo.imgurapi.ImgurApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ImgurApiModule {

    @Provides
    @Singleton
    fun provideImgurApi(retrofit: Retrofit): ImgurApi {
        return retrofit.create(ImgurApi::class.java)
    }
}