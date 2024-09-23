package kr.lul.blog.navigation.guide.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import kr.lul.blog.navigation.guide.ui.screen.FirstScreen
import kr.lul.blog.navigation.guide.ui.screen.SecondScreen
import kr.lul.blog.navigation.guide.ui.screen.SplashScreen
import kr.lul.blog.navigation.guide.ui.screen.ThirdScreen
import kr.lul.blog.navigation.guide.ui.theme.NavigationTheme

@Composable
fun Root(
    navHostController: NavHostController = rememberNavController()
) {
    NavigationTheme {
        NavHost(navHostController, "splash") {
            composable("splash") {
                SplashScreen(openFirst = { navHostController.first() })
            }
            composable("first") {
                FirstScreen(
                    onClickSecond = navHostController::second,
                    onClickThird = navHostController::third
                )
            }
            composable(route = "second") {
                SecondScreen(
                    onClickThird = navHostController::third,
                    onClickBack = navHostController::popBackStack
                )
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
                    args = it.toRoute(),
                    onClickBack = navHostController::popBackStack
                )
            }
        }
    }
}