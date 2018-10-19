package com.skyous.trakkit.di.module

import android.app.Application
import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.skyous.trakkit.data.MovieDbRestApi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Network level dagger module
 *
 * @author Arnold Szabo
 * @since 10/18/2018
 */

@Module
class NetworkModule(private val baseUrl: String) {

    @Provides
    @Singleton
    fun provideHttpCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache((context as Application).getCacheDir(), cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.cache(cache)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideRestService(retrofit: Retrofit): MovieDbRestApi = retrofit.create(MovieDbRestApi::class.java)
}