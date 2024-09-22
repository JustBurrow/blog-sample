package kr.lul.blog.navigation.abstraction.ui.navigator

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlin.system.exitProcess

@Immutable
class ThirdNavigator(
    private val activity: Activity,
    private val navHostController: NavHostController
) : Navigator {
    companion object : Destination {
        override val routePattern = "third/{param1}?param2={param2}"

        override val arguments: List<NamedNavArgument> = listOf(
            navArgument("param1") {
                nullable = false
                type = NavType.IntType
            },
            navArgument("param2") {
                nullable = true
                type = NavType.StringType
            }
        )

        override val deepLinks: List<NavDeepLink> = emptyList()

        fun route(param1: Int) = "third/$param1"

        fun route(param1: Int, param2: String) = "third/$param1?param2=$param2"
    }

    override val destination: Destination = Companion

    override fun back() {
        navHostController.popBackStack()
    }

    override fun restart() {
        val intent = Intent(activity, activity::class.java)
        activity.startActivity(intent)
        exitProcess(0)
    }

    override fun exit() {
        exitProcess(0)
    }

    override fun reopen() {
        val intent = Intent(activity, activity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        activity.startActivity(intent)
        activity.finish()
    }

    override fun settings() {
        val intent = Intent(android.provider.Settings.ACTION_SETTINGS)
        activity.startActivity(intent)
    }

    override fun web(url: Uri) {
        val intent = Intent(Intent.ACTION_VIEW, url)
        activity.startActivity(intent)
    }

    override fun call(phoneNumber: Uri) {
        val intent = Intent(Intent.ACTION_DIAL, phoneNumber)
        activity.startActivity(intent)
    }
}