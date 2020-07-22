package com.ashish.imgursearchdemo.ui.search.presenter

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.ashish.imgursearchdemo.R
import com.ashish.imgursearchdemo.model.Image
import com.ashish.imgursearchdemo.model.source.remote.ImgurRemoteSource
import com.ashish.imgursearchdemo.ui.search.images.UiImage
import com.ashish.imgursearchdemo.ui.search.view.ImgurSearchView
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import javax.inject.Inject

/**
 * Presenter for the search results.
 */
@ActivityRetainedScoped
class ImgurSearchPresenter @Inject constructor(private val imageDataSource: ImgurRemoteSource) :
    ViewModel() {

    private var _view: ImgurSearchView? = null

    val view: ImgurSearchView?
        get() = _view

    @VisibleForTesting
    internal var searchResultDisposable: Disposable? = null

    fun bind(view: ImgurSearchView?) {
        this._view = view
    }

    fun search(searchText: String) {
        if (searchResultDisposable?.isDisposed == false) {
            searchResultDisposable?.dispose()
        }
        imageDataSource.getImages(searchText)
            .subscribe(getSuccessLambda(), getErrorLambda())
    }

    private fun getErrorLambda(): @NonNull Consumer<in Throwable> =
        Consumer { _view?.showMessageWithSearchViewVisible(R.string.error_loading) }

    private fun getSuccessLambda(): @NonNull Consumer<List<Image>> =
        Consumer { imagesFromApi ->
            val images = imagesFromApi.map { UiImage.fromImage(it) }
            if (images.isEmpty()) {
                _view?.showMessageWithSearchViewVisible(R.string.no_results_available)
            } else {
                _view?.showContentsView()
                _view?.updateRecyclerView(images)
            }
        }

    fun onNetworkAvailable() {
        _view?.showContentsView()
    }

    fun onNetworkLost() {
        _view?.showErrorView(R.string.network_not_available)
    }

    fun unbind() {
        if (searchResultDisposable?.isDisposed == false) {
            searchResultDisposable?.dispose()
        }
        _view = null
    }
}