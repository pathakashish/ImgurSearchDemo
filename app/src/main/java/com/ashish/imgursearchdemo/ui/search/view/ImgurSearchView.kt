package com.ashish.imgursearchdemo.ui.search.view

import com.ashish.imgursearchdemo.ui.search.images.UiImage

/**
 * Contract Fragment, Activity and/View to follow to support search function in this app.
 */
interface ImgurSearchView {
    fun showContentsView()

    fun showErrorView(errorMessageResId: Int)

    fun updateRecyclerView(images: List<UiImage>)

    fun showMessageWithSearchViewVisible(messageResId: Int)
}