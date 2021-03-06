package com.georgcantor.hilt.util.navigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator

@Navigator.Name("fragment")
class MyFragmentNavigator(
    context: Context,
    fragmentManager: FragmentManager,
    containerId: Int
) : FragmentNavigator(context, fragmentManager, containerId) {

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        val shouldSkip = navOptions?.run {
            popUpTo == destination.id && !isPopUpToInclusive
        } ?: false

        return if (shouldSkip) null else super.navigate(destination, args, navOptions, navigatorExtras)
    }
}
