package com.ashish.imgursearchdemo.ui.search.presenter

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.ashish.imgursearchdemo.R
import com.ashish.imgursearchdemo.model.Image
import com.ashish.imgursearchdemo.model.source.remote.ImgurRemoteSource
import com.ashish.imgursearchdemo.ui.search.images.UiImage
import com.ashish.imgursearchdemo.ui.search.view.ImgurSearchView
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@ActivityRetainedScoped
class ImgurSearchPresenter @Inject constructor(private val imageDataSource: ImgurRemoteSource) :
    ViewModel() {

    @VisibleForTesting
    internal var view: ImgurSearchView? = null

    @VisibleForTesting
    internal var searchResultDisposable: Disposable? = null

    fun bind(view: ImgurSearchView?) {
        this.view = view
    }

    fun search(searchText: String) {
        if (searchResultDisposable?.isDisposed == false) {
            searchResultDisposable?.dispose()
        }
        imageDataSource.getImages(searchText)
            .subscribe(getSuccessLambda(), getErrorLambda())
    }

    private fun getErrorLambda(): @NonNull Consumer<in Throwable> =
        Consumer { view?.showErrorView(R.string.error_loading) }

    private fun getSuccessLambda(): @NonNull Consumer<List<Image>> =
        Consumer { imagesFromApi ->
            val images = imagesFromApi.map { UiImage.fromImage(it) }
            if (images.isEmpty()) {
                view?.showEmptyView()
            } else {
                view?.showContentsView()
                view?.updateRecyclerView(images)
            }
        }

    fun onNetworkAvailable() {
        view?.showContentsView()
    }

    fun onNetworkLost() {
        view?.showErrorView(R.string.network_not_available)
    }

    fun unbind() {
        if (searchResultDisposable?.isDisposed == false) {
            searchResultDisposable?.dispose()
        }
        view = null
    }
}