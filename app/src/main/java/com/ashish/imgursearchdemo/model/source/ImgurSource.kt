package com.ashish.imgursearchdemo.model.source

import com.ashish.imgursearchdemo.model.Image
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

/**
 * Imgur data source interface which can be implemented by different data sources.
 */
interface ImgurSource {
    fun getImages(searchText: String): Single<List<Image>>
}