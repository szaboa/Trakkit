package com.skyous.trakkit.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bumptech.glide.RequestManager

/**
 * TODO
 *
 * @author Arnold Szabo
 * @since 10/20/2018
 *
 */
class HorizontalStripeAdapter(val context: Context, requestManager: RequestManager) : AbstractBaseViewModelAdapter(requestManager) {

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}