package kr.lul.blog.navigation.abstraction.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController

@Immutable
class SecondNavigator(
    private val navHostController: NavHostController
) : Navigator {
    companion object : Destination {
        override val routePattern = "second"

        override val arguments: List<NamedNavArgument> = emptyList()

        override val deepLinks: List<NavDeepLink> = emptyList()

        fun route() = routePattern
    }

    fun third(param1: Int, param2: String) {
        navHostController.navigate(ThirdNavigator.route(param1, param2))
    }

    override fun back() {
        navHostController.popBackStack()
    }
}