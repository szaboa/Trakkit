package com.skyous.trakkit.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import com.bumptech.glide.RequestManager

/**
 * Custom view for horizontal stripe
 *
 * @author Arnold Szabo
 * @since 10/19/2018
 *
 */
class HorizontalStripeView : RecyclerView {

    lateinit var horizontalStripeAdapter: HorizontalStripeAdapter

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun initialize(requestManager: RequestManager) {
        horizontalStripeAdapter = HorizontalStripeAdapter(context, requestManager)
        layoutManager = horizontalStripeAdapter.getLayoutManager()
        adapter = horizontalStripeAdapter
        overScrollMode = View.OVER_SCROLL_NEVER
        clipToPadding = false
        setHasFixedSize(true)
    }
}