package com.skyous.trakkit

import android.app.Application
import com.skyous.trakkit.di.ApplicationComponent
import com.skyous.trakkit.di.DaggerApplicationComponent
import com.skyous.trakkit.di.module.ApplicationModule
import com.skyous.trakkit.di.module.NetworkModule
import com.skyous.trakkit.di.module.RepositoryModule

/**
 * @author Arnold Szabo
 * @since 10/18/2018
 */
class TrakkitApplication : Application() {

    companion object {
        private lateinit var applicationComponent: ApplicationComponent
        fun getComponent(): ApplicationComponent = applicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = initDagger()
    }

    /**
     * Initialize dagger component
     */
    private fun initDagger(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .networkModule(NetworkModule("https://..."))
                .repositoryModule(RepositoryModule())
                .build()
    }
}