package com.ashish.imgursearchdemo.model.source.remote

import com.ashish.imgursearchdemo.imgurapi.ImgurApi
import com.ashish.imgursearchdemo.model.Image
import com.ashish.imgursearchdemo.model.source.ImgurSource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Implementation of [ImgurSource] which deals with imgur web API.
 */
class ImgurRemoteSource @Inject constructor(private val imgurApi: ImgurApi) : ImgurSource {

    private var pageNumber = 0

    /**
     * Fetches the [com.ashish.imgursearchdemo.model.SearchResult] from Imgur search API and returns
     * list of [Image]s we can show. It filters out the images/videos which we cannot show.
     */
    override fun getImages(searchText: String): Single<List<Image>> {
        return imgurApi.search(pageNumber, searchText)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { result -> result.data.filter { it.isSupported() } }
            .map { dataList ->
                dataList.fold(mutableListOf<Image>(), { images, data ->
                    images.addAll(data.supportedImagesWithData())
                    images
                })
            }
    }
}