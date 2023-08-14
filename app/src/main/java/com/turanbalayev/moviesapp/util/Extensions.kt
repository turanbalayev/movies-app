package com.turanbalayev.moviesapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.turanbalayev.moviesapp.R
import com.turanbalayev.moviesapp.util.Constant.BASE_URL_IMAGE

fun ImageView.loadUrl(url: String) {
    val options = RequestOptions().centerCrop().placeholder(R.drawable.avatar)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .dontAnimate()
        .dontTransform()

    Glide.with(this).load(BASE_URL_IMAGE + url).apply(options).into(this)

}