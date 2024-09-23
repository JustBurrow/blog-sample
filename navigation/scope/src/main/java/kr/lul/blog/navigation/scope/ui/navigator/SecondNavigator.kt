package kr.lul.blog.navigation.scope.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NavHostController

@Immutable
class SecondNavigator(
    private val navHostController: NavHostController
) {
    fun third(param1: Int, param2: String) {
        navHostController.navigate("third/$param1?param2=$param2")
    }

    fun back() {
        navHostController.popBackStack()
    }
}