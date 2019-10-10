package com.skyous.trakkit.ui.component

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager

/**
 * TODO
 *
 * @author Arnold Szabo
 * @since 10/20/2018
 *
 */
class HorizontalStripeAdapter(val context: Context, requestManager: RequestManager) : AbstractBaseViewModelAdapter(requestManager) {

    override fun getLayoutManager(): androidx.recyclerview.widget.RecyclerView.LayoutManager {
        return androidx.recyclerview.widget.LinearLayoutManager(context, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false)
    }
}