package com.skyous.trakkit.data

import com.skyous.trakkit.data.dto.response.TvSeriesResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Retrofit rest service that communicates with themoviedb's API
 *
 * @author Arnold Szabo
 * @since 10/18/2018
 *
 */
interface MovieDbRestApi {

    @GET("/../..")
    fun getPopularList(): Single<List<TvSeriesResponse>>
}