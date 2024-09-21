package kr.lul.blog.navigation.abstraction.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Immutable
class ThirdNavigator(
    private val navHostController: NavHostController
) : Navigator {
    companion object : Destination {
        override val routePattern = "third/{param1}?param2={param2}"

        override val arguments: List<NamedNavArgument> = listOf(
            navArgument("param1") {
                nullable = false
                type = NavType.IntType
            },
            navArgument("param2") {
                nullable = true
                type = NavType.StringType
            }
        )

        override val deepLinks: List<NavDeepLink> = emptyList()

        fun route(param1: Int) = "third/$param1"

        fun route(param1: Int, param2: String) = "third/$param1?param2=$param2"
    }

    override fun back() {
        navHostController.popBackStack()
    }
}