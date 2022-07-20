package com.obvious.nasapicturesapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * Loads URL into an ImageView using Picasso
 *
 * @param url URL to be loaded
 */
fun ImageView.loadFromUrl(url: String) {
//    Picasso.get().load(url).into(this)
}

/**
 * Loads URL into an ImageView using Glide
 *
 * @param url URL to be loaded
 */
fun ImageView.loadFromUrl(url: String, context: Context) {
    Glide.with(context).load(url).into(this)
}
fun ImageView.loadFromUrlCornerRadius(url: String, context: Context) {
    val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//    Glide.with(context).load(url).apply(requestOptions).into(imageView)
    Glide.with(context).load(url).apply(requestOptions).centerCrop().into(this)
}