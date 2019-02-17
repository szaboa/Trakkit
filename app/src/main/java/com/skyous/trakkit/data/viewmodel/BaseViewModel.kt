package com.skyous.trakkit.data.viewmodel

import java.lang.IllegalArgumentException

/**
 * @author Arnold Szabo
 * @since 10/19/2018
 *
 */
abstract class BaseViewModel {
    enum class Type(val type: Int) {
        SERIES(1),
        ACTOR(2),
        GENRE(3);

        companion object {
            fun from(i: Int): Type {
                return when (i) {
                    1 -> SERIES
                    2 -> ACTOR
                    3 -> GENRE
                    else -> throw IllegalArgumentException("No matching type")
                }
            }
        }
    }

    abstract fun getType(): Type
}