package kr.lul.blog.navigation.screenobject.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController

@Immutable
class FirstNavigator(
    private val navHostController: NavHostController
) {
    companion object {
        const val routePattern = "first"

        val arguments: List<NamedNavArgument> = listOf()

        val deepLinks: List<NavDeepLink> = listOf()
    }

    fun second() {
        navHostController.navigate(SecondNavigator.routePatten)
    }

    fun third(param1: Int) {
        navHostController.navigate("third/$param1")
    }
}