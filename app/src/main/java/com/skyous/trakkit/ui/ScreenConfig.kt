package com.skyous.trakkit.ui

import android.content.Context
import android.util.TypedValue
import com.skyous.trakkit.R

/**
 * TODO
 *
 * @author Arnold Szabo
 * @since 10/21/2018
 *
 */
class ScreenConfig(val context: Context) {

    companion object {
        lateinit var tileSize: SeriesTileSize
    }

    init {
        val displayMetrics = context.resources.displayMetrics
        val stripeMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(R.dimen.stripe_margin), displayMetrics)
        val assetMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(R.dimen.asset_margin), displayMetrics)

        val width = (((displayMetrics.widthPixels - 2 * stripeMargin) / 3) - assetMargin).toInt()
        val height = width * 3 / 2

        tileSize = SeriesTileSize(width, height)
    }

    fun getTileSize(): SeriesTileSize = tileSize

    data class SeriesTileSize(var width: Int, var height: Int)
}