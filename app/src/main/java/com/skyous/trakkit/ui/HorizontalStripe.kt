package com.skyous.trakkit.ui

/**
 * Model class representing a horizontal stripe
 *
 * @author Arnold Szabo
 * @since 10/19/2018
 *
 */
data class HorizontalStripe<T : BaseViewModel>(val title: String, val list: List<BaseViewModel>) : BaseVerticalComponent<T>() {
    override fun getType(): Type = Type.STRIPE
}