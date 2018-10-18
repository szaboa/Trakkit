package com.skyous.trakkit.data.repository

import com.skyous.trakkit.data.dto.response.TvSeriesResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * The main repository that provides access to our data through different {@link TvSeriesDataSource}s
 *
 * @see TvSeriesRemoteDataSource
 * @see TvSeriesLocalDataSource
 *
 * @author Arnold Szabo
 * @since 10/18/2018
 *
 */
class TvSeriesRepository @Inject constructor(
        var remoteDataSource: TvSeriesRemoteDataSource,
        var localDataSource: TvSeriesLocalDataSource) : TvSeriesDataSource {

    override fun getPopularTvSeries(): Single<List<TvSeriesResponse>> {
        return remoteDataSource.getPopularTvSeries()
    }
}