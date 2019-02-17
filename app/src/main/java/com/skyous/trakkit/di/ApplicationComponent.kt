package com.skyous.trakkit.di

import com.skyous.trakkit.di.module.ApplicationModule
import com.skyous.trakkit.di.module.NetworkModule
import com.skyous.trakkit.di.module.RepositoryModule
import com.skyous.trakkit.ui.component.AbstractBaseViewModelAdapter
import com.skyous.trakkit.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Application level dagger component
 *
 * @author Arnold Szabo
 * @since 10/18/2018
 *
 */

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, RepositoryModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(adapter: AbstractBaseViewModelAdapter)
}