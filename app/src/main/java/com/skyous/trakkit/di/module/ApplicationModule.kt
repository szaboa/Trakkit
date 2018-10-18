package com.skyous.trakkit.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application level dagger module
 *
 * @author Arnold Szabo
 * @since 10/18/2018
 *
 */

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideApplicationContext() = context
}