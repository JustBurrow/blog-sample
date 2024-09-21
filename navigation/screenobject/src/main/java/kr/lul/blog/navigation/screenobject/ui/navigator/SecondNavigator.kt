package kr.lul.blog.navigation.screenobject.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NavHostController

@Immutable
class SecondNavigator(
    private val navHostController: NavHostController
) {
    companion object {
        const val routePatten = "second"

        fun route() = routePatten
    }

    fun third(param1: Int, param2: String) {
        navHostController.navigate(ThirdNavigator.route(param1, param2))
    }

    fun back() {
        navHostController.popBackStack()
    }
}