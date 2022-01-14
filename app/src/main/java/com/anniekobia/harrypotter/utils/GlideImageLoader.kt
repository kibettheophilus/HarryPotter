package com.anniekobia.harrypotter.utils

import android.content.Context
import android.widget.ImageView
import com.anniekobia.harrypotter.R
import com.bumptech.glide.Glide

/**
 * Load Image from cache, if no image try fetching the image online, backup placeholder
 */
fun ImageView.loadImageUrl(imageUrl: String, context: Context) {
    if (imageUrl.isBlank()){
        Glide.with(context)
            .load("empty")
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.placeholder_image)
            .into(this@loadImageUrl)
    } else {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.placeholder_image)
            .into(this@loadImageUrl)
    }
}