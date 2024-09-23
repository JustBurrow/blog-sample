package kr.lul.blog.navigation.abstraction.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.toRoute
import kr.lul.blog.navigation.abstraction.ui.navigator.BaseNavigator
import kr.lul.blog.navigation.abstraction.ui.navigator.FirstNavigator
import kr.lul.blog.navigation.abstraction.ui.navigator.SecondNavigator
import kr.lul.blog.navigation.abstraction.ui.navigator.SplashNavigator
import kr.lul.blog.navigation.abstraction.ui.navigator.ThirdNavigator
import kr.lul.blog.navigation.abstraction.ui.navigator.composable
import kr.lul.blog.navigation.abstraction.ui.screen.FirstScreen
import kr.lul.blog.navigation.abstraction.ui.screen.SecondScreen
import kr.lul.blog.navigation.abstraction.ui.screen.SplashScreen
import kr.lul.blog.navigation.abstraction.ui.screen.ThirdScreen
import kr.lul.blog.navigation.abstraction.ui.theme.NavigationTheme

@Composable
fun Root(
    baseNavigator: BaseNavigator
) {
    NavigationTheme {
        NavHost(
            navController = baseNavigator.navHostController,
            startDestination = baseNavigator.destination.routePattern,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(SplashNavigator(baseNavigator)) { _, navigator ->
                SplashScreen(navigator)
            }
            composable(FirstNavigator(baseNavigator)) { _, navigator ->
                FirstScreen(navigator)
            }
            composable(SecondNavigator(baseNavigator)) { _, navigator ->
                SecondScreen(navigator)
            }
            composable(ThirdNavigator(baseNavigator)) { backStackEntry, navigator ->
                ThirdScreen(
                    navigator = navigator,
                    args = backStackEntry.toRoute()
                )
            }
        }
    }
}