package com.skyous.trakkit.data.repository

import com.skyous.trakkit.data.dto.response.TvSeriesResponse
import io.reactivex.Single

/**
 * Specific implementation of the {@link TvSeriesDataSource} that provides
 * access to the locally cached TV series related data
 *
 * @author Arnold Szabo
 * @since 10/18/2018
 *
 */
class TvSeriesLocalDataSource : TvSeriesDataSource {
    override fun getPopularTvSeries(): Single<List<TvSeriesResponse>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}