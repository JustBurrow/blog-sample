package kr.lul.blog.navigation.scope.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NavHostController

@Immutable
class ThirdNavigator(
    private val navHostController: NavHostController
) {
    fun back() {
        navHostController.popBackStack()
    }
}