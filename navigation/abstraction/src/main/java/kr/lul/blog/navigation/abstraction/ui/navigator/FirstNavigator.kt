package kr.lul.blog.navigation.abstraction.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController

@Immutable
class FirstNavigator(
    private val navHostController: NavHostController
) : Navigator {
    companion object : Destination {
        override val routePattern = "first"

        override val arguments: List<NamedNavArgument> = emptyList()

        override val deepLinks: List<NavDeepLink> = emptyList()

        fun route() = routePattern
    }

    fun second() {
        navHostController.navigate(SecondNavigator.route())
    }

    fun third(param1: Int) {
        navHostController.navigate(ThirdNavigator.route(param1))
    }

    override fun back() {
        navHostController.popBackStack()
    }
}