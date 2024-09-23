package kr.lul.blog.navigation.artifact

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Immutable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlin.system.exitProcess

@Immutable
open class BaseNavigator(
    /**
     * Single Activity 구조의 [Activity].
     *
     * 참고 : [Single Activity: Why, When, and How](https://www.youtube.com/2k8x8V77CrU)
     */
    val activity: Activity,
    val navHostController: NavHostController,
    /**
     * [NavHost]의 `startDestination`
     *
     * @see androidx.navigation.compose.NavHost
     */
    override val destination: Destination
) : Navigator {
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
        activity.startActivity(Intent(android.provider.Settings.ACTION_SETTINGS))
    }

    override fun web(url: Uri) {
        activity.startActivity(Intent(Intent.ACTION_VIEW, url))
    }

    override fun call(phoneNumber: Uri) {
        activity.startActivity(Intent(Intent.ACTION_DIAL, phoneNumber))
    }
}