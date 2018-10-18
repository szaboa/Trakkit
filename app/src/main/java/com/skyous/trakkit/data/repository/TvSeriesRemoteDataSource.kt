package com.skyous.trakkit.data.repository

import com.skyous.trakkit.data.MovieDbRestApi
import com.skyous.trakkit.data.dto.response.TvSeriesResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Specific implementation of the {@link TvSeriesDataSource} that provides
 * access to the TV series related data on the backend
 *
 * @see MovieDbRestApi
 *
 * @author Arnold Szabo
 * @since 10/18/2018
 *
 */
class TvSeriesRemoteDataSource @Inject constructor(var api: MovieDbRestApi) : TvSeriesDataSource {

    override fun getPopularTvSeries(): Single<List<TvSeriesResponse>> {
        return api.getPopularList()
    }
}