package kr.lul.blog.navigation.experience.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

@Immutable
class SecondNavigator(
    private val base: BaseNavigator
) : Navigator by base {
    companion object : Destination {
        override val routePattern = "second"

        override val arguments: List<NamedNavArgument> = emptyList()

        override val deepLinks: List<NavDeepLink> = emptyList()

        fun route() = routePattern
    }

    fun third(param1: Int, param2: String) {
        base.navHostController.navigate(ThirdNavigator.route(param1, param2))
    }

    override val destination: Destination = Companion
}