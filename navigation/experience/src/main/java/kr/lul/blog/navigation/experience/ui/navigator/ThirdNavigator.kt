package kr.lul.blog.navigation.experience.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kr.lul.blog.navigation.artifact.BaseNavigator
import kr.lul.blog.navigation.artifact.Destination
import kr.lul.blog.navigation.artifact.Navigator

@Immutable
class ThirdNavigator(
    private val base: BaseNavigator
) : Navigator by base {
    companion object : Destination {
        const val ARG_PARAM1 = "param1"
        const val ARG_PARAM2 = "param2"

        override val routePattern = "third/{$ARG_PARAM1}?$ARG_PARAM2={$ARG_PARAM2}"

        override val arguments: List<NamedNavArgument> = listOf(
            navArgument(ARG_PARAM1) {
                nullable = false
                type = NavType.IntType
            },
            navArgument(ARG_PARAM2) {
                nullable = true
                type = NavType.StringType
            }
        )

        override val deepLinks: List<NavDeepLink> = emptyList()

        fun route(param1: Int) = "third/$param1"

        fun route(param1: Int, param2: String) = "third/$param1?param2=$param2"
    }

    override val destination: Destination = Companion
}