package com.skyous.trakkit.data.dto.response

import com.google.gson.annotations.SerializedName

/**
 * Response model for a TV series
 *
 * @author Arnold Szabo
 * @since 10/18/2018
 */

data class TvSeriesResponse(
        @SerializedName("id") val id: Int
)