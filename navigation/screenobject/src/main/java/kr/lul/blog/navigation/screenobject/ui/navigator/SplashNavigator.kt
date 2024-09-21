package kr.lul.blog.navigation.screenobject.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NavHostController

@Immutable
class SplashNavigator(
    private val navHostController: NavHostController
) {
    companion object {
        const val routePattern = "splash"

        fun route() = routePattern
    }

    fun first() {
        navHostController.popBackStack()
        navHostController.navigate(FirstNavigator.route())
    }
}