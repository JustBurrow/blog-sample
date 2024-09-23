package kr.lul.blog.navigation.screenobject.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kr.lul.blog.navigation.screenobject.ui.navigator.FirstNavigator
import kr.lul.blog.navigation.screenobject.ui.navigator.SecondNavigator
import kr.lul.blog.navigation.screenobject.ui.navigator.SplashNavigator
import kr.lul.blog.navigation.screenobject.ui.navigator.ThirdNavigator
import kr.lul.blog.navigation.screenobject.ui.screen.FirstScreen
import kr.lul.blog.navigation.screenobject.ui.screen.SecondScreen
import kr.lul.blog.navigation.screenobject.ui.screen.SplashScreen
import kr.lul.blog.navigation.screenobject.ui.screen.ThirdScreen
import kr.lul.blog.navigation.screenobject.ui.theme.NavigationTheme

@Composable
fun Root(
    navHostController: NavHostController = rememberNavController()
) {
    NavigationTheme {
        NavHost(navHostController, SplashNavigator.route()) {
            composable(SplashNavigator.routePattern) {
                SplashScreen(navigator = SplashNavigator(navHostController))
            }
            composable(FirstNavigator.routePattern) {
                FirstScreen(navigator = FirstNavigator(navHostController))
            }
            composable(SecondNavigator.routePattern) {
                SecondScreen(navigator = SecondNavigator(navHostController))
            }
            composable(ThirdNavigator.routePattern, ThirdNavigator.arguments) {
                ThirdScreen(
                    navigator = ThirdNavigator(navHostController),
                    args = it.toRoute()
                )
            }
        }
    }
}