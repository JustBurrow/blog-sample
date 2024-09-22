package kr.lul.blog.navigation.abstraction.ui

import android.app.Activity
import androidx.compose.runtime.Composable
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
        NavHost(navHostController, SplashNavigator.route()) {
            composable(SplashNavigator) {
                SplashScreen(navigator = SplashNavigator(activity, navHostController))
            }
            composable(FirstNavigator) {
                FirstScreen(navigator = FirstNavigator(activity, navHostController))
            }
            composable(SecondNavigator) {
                SecondScreen(navigator = SecondNavigator(activity, navHostController))
            }
            composable(ThirdNavigator) {
                ThirdScreen(
                    navigator = ThirdNavigator(activity, navHostController),
                    args = it.toRoute()
                )
            }
        }
    }
}