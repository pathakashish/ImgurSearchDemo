package com.ashish.imgursearchdemo.ui.search

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashish.imgursearchdemo.R
import com.ashish.imgursearchdemo.model.source.remote.ImgurRemoteSource
import com.ashish.imgursearchdemo.ui.search.images.UiImage
import com.ashish.imgursearchdemo.ui.search.presenter.ImgurSearchPresenter
import com.ashish.imgursearchdemo.ui.search.view.ImgurSearchView
import com.ashish.imgursearchdemo.utils.isNetworkAvailable
import com.jakewharton.rxbinding4.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class ImgurImageSearchActivity : AppCompatActivity(), ImgurSearchView {

    private var textWatcher: Disposable? = null

    @Inject
    lateinit var imageDataSource: ImgurRemoteSource

    @Inject
    lateinit var searchPresenter: ImgurSearchPresenter

    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.imgurimage_list) }
    private val textView: TextView by lazy { findViewById<TextView>(R.id.error_textview) }
    private val searchEditText: EditText by lazy { findViewById<EditText>(R.id.search_edittext) }

    private var detailsContainer: View? = null
    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchPresenter.bind(this)
        setContentView(R.layout.activity_imgurimage_list)
        detailsContainer = findViewById(R.id.imgurimage_detail_container)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        if (detailsContainer != null) {
            twoPane = true
        }
        setSearchEditText(findViewById(R.id.search_edittext))
        networkCallback = getNetworkCallback()
    }

    override fun onResume() {
        super.onResume()
        if (isNetworkAvailable(this)) {
            searchPresenter.onNetworkAvailable()
        } else {
            searchPresenter.onNetworkLost()
        }
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(networkCallback!!)
    }

    override fun onPause() {
        super.onPause()

        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        networkCallback = null
        searchPresenter.unbind()
        textWatcher?.dispose()
    }

    private fun getNetworkCallback() = object :
        ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            runOnUiThread {
                searchPresenter.onNetworkAvailable()
            }
        }

        override fun onLost(network: Network?) {
            runOnUiThread {
                searchPresenter.onNetworkLost()
            }
        }
    }

    private fun setSearchEditText(searchEditText: EditText) {
        textWatcher =
            searchEditText.textChanges().filter { charSequence -> charSequence.length > 1 }
                .debounce(250, TimeUnit.MILLISECONDS)
                .map { charSequence -> charSequence.toString() }
                .subscribe { searchText -> searchPresenter.search(searchText) }
    }

    override fun showContentsView() {
        textView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        searchEditText.visibility = View.VISIBLE
        detailsContainer?.visibility = View.VISIBLE
    }

    override fun showErrorView(errorMessageResId: Int) {
        textView.setText(errorMessageResId)
        textView.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE
        searchEditText.visibility = View.INVISIBLE
        detailsContainer?.visibility = View.INVISIBLE
    }

    override fun updateRecyclerView(images: List<UiImage>) {
        val imageViewSize = resources.getDimensionPixelSize(R.dimen.image_cell_size)
        val spanCount = recyclerView.measuredWidth / imageViewSize
        recyclerView.layoutManager = GridLayoutManager(this, spanCount)
        recyclerView.adapter = ImageAdapter(this, images, twoPane)
    }

    override fun showMessageWithSearchViewVisible(messageResId: Int) {
        textView.setText(messageResId)
        searchEditText.visibility = View.VISIBLE
        textView.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE
        detailsContainer?.visibility = View.INVISIBLE
    }
}