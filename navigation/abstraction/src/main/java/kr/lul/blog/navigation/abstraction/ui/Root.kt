package kr.lul.blog.navigation.abstraction.ui

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
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
    activity: Activity,
    navHostController: NavHostController = rememberNavController()
) {
    NavigationTheme {
        NavHost(
            navController = navHostController,
            startDestination = SplashNavigator.route(),
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(SplashNavigator(activity, navHostController)) { _, navigator ->
                SplashScreen(navigator)
            }
            composable(FirstNavigator(activity, navHostController)) { _, navigator ->
                FirstScreen(navigator)
            }
            composable(SecondNavigator(activity, navHostController)) { _, navigator ->
                SecondScreen(navigator)
            }
            composable(ThirdNavigator(activity, navHostController)) { backStackEntry, navigator ->
                ThirdScreen(
                    navigator = navigator,
                    args = backStackEntry.toRoute()
                )
            }
        }
    }
}