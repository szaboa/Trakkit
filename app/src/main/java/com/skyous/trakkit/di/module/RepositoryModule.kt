package com.skyous.trakkit.di.module

import com.skyous.trakkit.data.MovieDbRestApi
import com.skyous.trakkit.data.repository.TvSeriesLocalDataSource
import com.skyous.trakkit.data.repository.TvSeriesRemoteDataSource
import com.skyous.trakkit.data.repository.TvSeriesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module for the data repository
 *
 * @author Arnold Szabo
 * @since 10/18/2018
 */

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideLocalDataSource() = TvSeriesLocalDataSource()

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: MovieDbRestApi) = TvSeriesRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideRepository(remote: TvSeriesRemoteDataSource, local: TvSeriesLocalDataSource) = TvSeriesRepository(remote, local)
}