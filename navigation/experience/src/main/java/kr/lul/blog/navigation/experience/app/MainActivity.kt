package kr.lul.blog.navigation.experience.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import kr.lul.blog.navigation.artifact.rememberBaseNavigator
import kr.lul.blog.navigation.experience.ui.Root
import kr.lul.blog.navigation.experience.ui.navigator.SplashNavigator

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            Root(rememberBaseNavigator(this, SplashNavigator))
        }
    }
}