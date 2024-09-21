package kr.lul.blog.navigation.guide.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                    onClickSecond = { navHostController.second() },
                    onClickThird = { navHostController.third() }
                )
            }
            composable("second") {
                SecondScreen(
                    onClickThird = { navHostController.third() },
                    onClickBack = { navHostController.popBackStack() }
                )
            }
            composable("third") {
                ThirdScreen(onClickBack = { navHostController.popBackStack() })
            }
        }
    }
}