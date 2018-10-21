package com.skyous.trakkit.ui

/**
 * TODO
 *
 * @author Arnold Szabo
 * @since 10/20/2018
 *
 */

data class SeriesViewModel(val title: String) : BaseViewModel() {
    override fun getType(): Type = Type.SERIES
}