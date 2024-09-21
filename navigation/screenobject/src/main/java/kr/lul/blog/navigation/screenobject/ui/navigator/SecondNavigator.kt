package kr.lul.blog.navigation.screenobject.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController

@Immutable
class SecondNavigator(
    private val navHostController: NavHostController
) {
    companion object {
        const val routePatten = "second"

        val arguments: List<NamedNavArgument> = listOf()

        val deepLinks: List<NavDeepLink> = listOf()
    }

    fun third(param1: Int, param2: String) {
        navHostController.navigate("third/$param1?param2=$param2")
    }

    fun back() {
        navHostController.popBackStack()
    }
}