package kr.lul.blog.navigation.abstraction.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController

@Immutable
class SplashNavigator(
    private val navHostController: NavHostController
) : Navigator {
    companion object : Destination {
        override val routePattern = "splash"

        override val arguments: List<NamedNavArgument> = emptyList()

        override val deepLinks: List<NavDeepLink> = emptyList()

        fun route() = routePattern
    }

    fun first() {
        navHostController.popBackStack()
        navHostController.navigate(FirstNavigator.route())
    }

    override fun back() {
        throw UnsupportedOperationException()
    }
}