package kr.lul.blog.navigation.experience.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.toRoute
import kr.lul.blog.navigation.artifact.BaseNavigator
import kr.lul.blog.navigation.artifact.composable
import kr.lul.blog.navigation.artifact.rememberBaseNavigator
import kr.lul.blog.navigation.experience.ui.navigator.FirstNavigator
import kr.lul.blog.navigation.experience.ui.navigator.SecondNavigator
import kr.lul.blog.navigation.experience.ui.navigator.SplashNavigator
import kr.lul.blog.navigation.experience.ui.navigator.ThirdNavigator
import kr.lul.blog.navigation.experience.ui.screen.FirstScreen
import kr.lul.blog.navigation.experience.ui.screen.SecondScreen
import kr.lul.blog.navigation.experience.ui.screen.SplashScreen
import kr.lul.blog.navigation.experience.ui.screen.ThirdScreen
import kr.lul.blog.navigation.experience.ui.theme.NavigationTheme

@Composable
fun Root(
    baseNavigator: BaseNavigator = rememberBaseNavigator()
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