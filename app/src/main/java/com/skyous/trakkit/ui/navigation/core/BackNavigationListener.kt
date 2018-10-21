package com.skyous.trakkit.ui.navigation.core

/**
 * Listener for MainNavigator handled back navigation
 *
 * @author Peter Abraham
 * @since 10/19/2018
 *
 */

interface BackNavigationListener {

    fun onNavigatedBack(tab: MainNavigator.Tab) {}
}