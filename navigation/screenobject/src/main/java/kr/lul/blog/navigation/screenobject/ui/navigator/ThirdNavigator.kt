package kr.lul.blog.navigation.screenobject.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Immutable
class ThirdNavigator(
    private val navHostController: NavHostController
) {
    companion object {
        const val routePatten = "third/{param1}?param2={param2}"

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

        val deepLinks: List<NavDeepLink> = listOf()
    }

    fun back() {
        navHostController.popBackStack()
    }
}