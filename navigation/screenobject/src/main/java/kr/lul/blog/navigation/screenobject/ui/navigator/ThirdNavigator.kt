package kr.lul.blog.navigation.screenobject.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Immutable
class ThirdNavigator(
    private val navHostController: NavHostController
) {
    companion object {
        const val routePattern = "third/{param1}?param2={param2}"

        val arguments: List<NamedNavArgument> = listOf(
            navArgument("param1") {
                nullable = false
                type = NavType.IntType
            },
            navArgument("param2") {
                nullable = true
                type = NavType.StringType
            }
        )

        fun route(param1: Int) = "third/$param1"

        fun route(param1: Int, param2: String) = "third/$param1?param2=$param2"
    }

    fun back() {
        navHostController.popBackStack()
    }
}