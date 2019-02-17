package com.skyous.trakkit.di.module

import android.content.Context
import com.skyous.trakkit.ui.navigation.core.MainNavigator
import com.skyous.trakkit.ui.util.ScreenConfig
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

    @Provides
    @Singleton
    fun provideScreenConfig(context: Context) = ScreenConfig(context)

    @Provides
    @Singleton
    fun provideMainNavigator() = MainNavigator()
}