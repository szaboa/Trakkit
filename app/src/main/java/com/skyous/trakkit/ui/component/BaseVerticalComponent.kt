package com.skyous.trakkit.ui.component

import com.skyous.trakkit.data.viewmodel.BaseViewModel
import java.lang.IllegalArgumentException

/**
 * Base class for vertical list items
 *
 * @author Arnold Szabo
 * @since 10/19/2018
 */
abstract class BaseVerticalComponent<T : BaseViewModel> {

    enum class Type(val type: Int) {
        STRIPE(1),
        BANNER(2);

        companion object {
            fun from(i: Int): Type {
                return when (i) {
                    1 -> STRIPE
                    2 -> BANNER
                    else -> throw IllegalArgumentException("No matching type")
                }
            }
        }
    }

    abstract fun getType(): Type
}