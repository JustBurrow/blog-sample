package kr.lul.blog.navigation.abstraction.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.lul.blog.navigation.abstraction.ui.Root
import kr.lul.blog.navigation.abstraction.ui.navigator.BaseNavigator
import kr.lul.blog.navigation.abstraction.ui.navigator.SplashNavigator

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Root(BaseNavigator(this, rememberNavController(), SplashNavigator))
        }
    }
}