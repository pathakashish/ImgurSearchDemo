package com.ashish.imgursearchdemo.di

import com.ashish.imgursearchdemo.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideBaseHttpsUrl(): HttpUrl {
        return HttpUrl.Builder()
            .scheme(BuildConfig.END_POINT_SCHEME)
            .host(BuildConfig.END_POINT_HOST)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory,
        httpUrl: HttpUrl
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(httpUrl)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
}