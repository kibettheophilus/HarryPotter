package com.anniekobia.harrypotter.utils

import android.widget.ImageView
import com.anniekobia.harrypotter.R
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception

/**
 * Load Image from cache, if no image try fetching the image online, backup placeholder
 */
fun loadImage(imageUrl: String, imageView: ImageView) {
    Picasso.get()
        .load(imageUrl)
        .networkPolicy(NetworkPolicy.OFFLINE)
        .into(imageView, object : Callback {
            override fun onSuccess() {}

            override fun onError(e: Exception?) {
                Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(imageView)
            }
        })
}