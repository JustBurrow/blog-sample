package kr.lul.blog.navigation.experience.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.toRoute
import kr.lul.blog.navigation.artifact.BaseNavigator
import kr.lul.blog.navigation.artifact.page
import kr.lul.blog.navigation.artifact.rememberBaseNavigator
import kr.lul.blog.navigation.experience.ui.navigator.FirstNavigator
import kr.lul.blog.navigation.experience.ui.navigator.SecondNavigator
import kr.lul.blog.navigation.experience.ui.navigator.SplashNavigator
import kr.lul.blog.navigation.experience.ui.navigator.ThirdNavigator
import kr.lul.blog.navigation.experience.ui.page.FirstPage
import kr.lul.blog.navigation.experience.ui.page.SecondPage
import kr.lul.blog.navigation.experience.ui.page.SplashPage
import kr.lul.blog.navigation.experience.ui.page.ThirdPage
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
            page(SplashNavigator(baseNavigator)) { _, navigator ->
                SplashPage(navigator)
            }
            page(FirstNavigator(baseNavigator)) { _, navigator ->
                FirstPage(navigator)
            }
            page(SecondNavigator(baseNavigator)) { _, navigator ->
                SecondPage(navigator)
            }
            page(ThirdNavigator(baseNavigator)) { backStackEntry, navigator ->
                ThirdPage(
                    navigator = navigator,
                    args = backStackEntry.toRoute()
                )
            }
        }
    }
}