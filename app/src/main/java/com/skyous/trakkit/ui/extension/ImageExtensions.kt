package com.skyous.trakkit.ui.extension

import android.widget.ImageView
import com.bumptech.glide.RequestManager

/**
 * TODO
 *
 * @author Arnold Szabo
 * @since 10/21/2018
 *
 */

fun ImageView.load(requestManager: RequestManager, url: String) {
    requestManager
            .load(url)
            .into(this)
}