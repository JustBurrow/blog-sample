package kr.lul.blog.navigation.scope.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NavHostController

@Immutable
class SplashNavigator(
    private val navHostController: NavHostController
) {
    fun first() {
        navHostController.popBackStack()
        navHostController.navigate("first")
    }
}