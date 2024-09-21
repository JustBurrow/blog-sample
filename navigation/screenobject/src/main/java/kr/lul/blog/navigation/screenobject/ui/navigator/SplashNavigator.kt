package kr.lul.blog.navigation.screenobject.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController

@Immutable
class SplashNavigator(
    private val navHostController: NavHostController
) {
    companion object {
        const val routePatten = "splash"

        val arguments: List<NamedNavArgument> = listOf()

        val deepLinks: List<NavDeepLink> = listOf()
    }

    fun first() {
        navHostController.popBackStack()
        navHostController.navigate(FirstNavigator.routePattern)
    }
}