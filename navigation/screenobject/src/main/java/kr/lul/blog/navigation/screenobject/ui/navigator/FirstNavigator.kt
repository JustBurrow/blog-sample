package kr.lul.blog.navigation.screenobject.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NavHostController

@Immutable
class FirstNavigator(
    private val navHostController: NavHostController
) {
    companion object {
        const val routePattern = "first"

        fun route() = routePattern
    }

    fun second() {
        navHostController.navigate(SecondNavigator.route())
    }

    fun third(param1: Int) {
        navHostController.navigate(ThirdNavigator.route(param1))
    }
}