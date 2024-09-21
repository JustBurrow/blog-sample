package kr.lul.blog.navigation.scope.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import kr.lul.blog.navigation.scope.ui.navigator.FirstNavigator
import kr.lul.blog.navigation.scope.ui.navigator.SecondNavigator
import kr.lul.blog.navigation.scope.ui.navigator.SplashNavigator
import kr.lul.blog.navigation.scope.ui.navigator.ThirdNavigator
import kr.lul.blog.navigation.scope.ui.screen.FirstScreen
import kr.lul.blog.navigation.scope.ui.screen.SecondScreen
import kr.lul.blog.navigation.scope.ui.screen.SplashScreen
import kr.lul.blog.navigation.scope.ui.screen.ThirdScreen
import kr.lul.blog.navigation.scope.ui.theme.NavigationTheme

@Composable
fun Root(
    navHostController: NavHostController = rememberNavController()
) {
    NavigationTheme {
        NavHost(navHostController, "splash") {
            composable("splash") {
                SplashScreen(navigator = SplashNavigator(navHostController))
            }
            composable("first") {
                FirstScreen(navigator = FirstNavigator(navHostController))
            }
            composable("second") {
                SecondScreen(navigator = SecondNavigator(navHostController))
            }
            composable(
                route = "third/{param1}?param2={param2}",
                arguments = listOf(
                    navArgument("param1") {
                        nullable = false
                        type = NavType.IntType
                    },
                    navArgument("param2") {
                        nullable = true
                        type = NavType.StringType
                    }
                )
            ) {
                ThirdScreen(
                    navigator = ThirdNavigator(navHostController),
                    args = it.toRoute()
                )
            }
        }
    }
}