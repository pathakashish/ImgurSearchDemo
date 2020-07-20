package com.ashish.imgursearchdemo.imgurapi

import com.ashish.imgursearchdemo.model.SearchResult
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurApi {
    @GET("3/gallery/search/{pageNumber}")
    fun search(@Path("pageNumber") pageNumber: Int, @Query("q") searchString: String): Flowable<SearchResult>
}