package com.ashish.imgursearchdemo.ui.search.view

import com.ashish.imgursearchdemo.ui.search.images.UiImage

interface ImgurSearchView {
    fun showContentsView()

    fun showErrorView(errorMessageResId: Int)

    fun updateRecyclerView(images: List<UiImage>)

    fun showMessageWithSearchViewVisible(messageResId: Int)
}