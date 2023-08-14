package com.turanbalayev.moviesapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapter {

    @BindingAdapter("load_image")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageUrl:String?){
        imageUrl?.let {
            imageView.loadUrl(imageUrl)
        }
    }
}