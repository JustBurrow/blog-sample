package kr.lul.blog.navigation.scope.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NavHostController

@Immutable
class FirstNavigator(
    private val navHostController: NavHostController
) {
    fun second() {
        navHostController.navigate("second")
    }

    fun third(param1: Int) {
        navHostController.navigate("third/$param1")
    }
}