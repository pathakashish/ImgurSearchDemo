package com.ashish.imgursearchdemo.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.ashish.imgursearchdemo.R
import com.ashish.imgursearchdemo.ui.search.images.UiImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.google.android.material.appbar.CollapsingToolbarLayout

/**
 * A fragment representing a single ImgurImage detail screen.
 * This fragment is either contained in a [ImgurImageSearchActivity]
 * in two-pane mode (on tablets) or a [ImgurImageDetailActivity]
 * on handsets.
 */
class ImgurImageDetailFragment : Fragment() {

    private var image: UiImage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(IMAGE)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                image = it.getParcelable<UiImage>(IMAGE)
                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title =
                    image?.title
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.imgurimage_detail, container, false)
        val circularProgressDrawable = CircularProgressDrawable(inflater.context)
        circularProgressDrawable.strokeWidth = resources.getDimension(R.dimen.stroke_width)
        circularProgressDrawable.centerRadius = resources.getDimension(R.dimen.progressbar_size_big)
        circularProgressDrawable.start()
        // Show the dummy content as text in a TextView.
        image?.let {
            val imageView = rootView.findViewById<ImageView>(R.id.imgurimage_detail)
            Glide
                .with(imageView)
                .load(it.link)
                .override(imageView.measuredWidth, imageView.measuredHeight)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(circularProgressDrawable)
                .error(R.drawable.ic_error)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(DrawableImageViewTarget(imageView))
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the Image ID that this fragment
         * represents.
         */
        const val IMAGE = "image"
    }
}