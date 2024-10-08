package kr.lul.blog.navigation.abstraction.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

@Immutable
class FirstNavigator(
    private val base: BaseNavigator
) : Navigator by base {
    companion object : Destination {
        override val routePattern = "first"

        override val arguments: List<NamedNavArgument> = emptyList()

        override val deepLinks: List<NavDeepLink> = emptyList()

        fun route() = routePattern
    }

    fun second() {
        base.navHostController.navigate(SecondNavigator.route())
    }

    fun third(param1: Int) {
        base.navHostController.navigate(ThirdNavigator.route(param1))
    }

    override val destination: Destination = Companion
}