package com.ashish.imgursearchdemo.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.ashish.imgursearchdemo.R
import com.ashish.imgursearchdemo.ui.search.images.UiImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget

class ImageAdapter(
    private val parentActivity: ImgurImageSearchActivity,
    private val values: List<UiImage>,
    private val twoPane: Boolean
) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val image = v.tag as UiImage
            if (twoPane) {
                val fragment = ImgurImageDetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ImgurImageDetailFragment.IMAGE, image)
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.imgurimage_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, ImgurImageDetailActivity::class.java).apply {
                    putExtra(ImgurImageDetailFragment.IMAGE, image)
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.imgurimage_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = values[position]
        with(holder.itemView) {
            tag = image
            setOnClickListener(onClickListener)
        }
        Glide
            .with(holder.imageView)
            .load(image.link)
            .override(holder.imageViewSize, holder.imageViewSize)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(holder.circularProgressDrawable)
            .error(R.drawable.ic_error)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.imageTarget)

    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageview)
        val imageTarget = DrawableImageViewTarget(imageView)
        val circularProgressDrawable = CircularProgressDrawable(view.context)
        val imageViewSize = view.resources.getDimensionPixelSize(R.dimen.image_cell_size)

        init {
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
        }
    }
}