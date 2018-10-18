package com.skyous.trakkit.data.repository

import com.skyous.trakkit.data.dto.response.TvSeriesResponse
import io.reactivex.Single

/**
 * Publicly exposed data layer to access TV series related data
 *
 * @see TvSeriesRemoteDataSource
 * @see TvSeriesLocalDataSource
 *
 * @author Arnold Szabo
 * @since 10/18/2018
 *
 */
interface TvSeriesDataSource {
    fun getPopularTvSeries(): Single<List<TvSeriesResponse>>
}