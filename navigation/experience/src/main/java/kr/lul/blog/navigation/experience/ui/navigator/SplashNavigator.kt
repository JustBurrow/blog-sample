package kr.lul.blog.navigation.experience.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import kr.lul.blog.navigation.artifact.BaseNavigator
import kr.lul.blog.navigation.artifact.Destination
import kr.lul.blog.navigation.artifact.Navigator

@Immutable
class SplashNavigator(
    private val base: BaseNavigator
) : Navigator by base {
    companion object : Destination {
        override val routePattern = "splash"

        override val arguments: List<NamedNavArgument> = emptyList()

        override val deepLinks: List<NavDeepLink> = emptyList()

        fun route() = routePattern
    }

    fun first() {
        base.navHostController.popBackStack()
        base.navHostController.navigate(FirstNavigator.route())
    }

    override val destination: Destination = Companion
}