package kr.lul.blog.navigation.experience.ui.navigator

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.PreviewActivity
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberBaseNavigator(
    activity: Activity = if (LocalInspectionMode.current) {
        // @Compose 디자인 프리뷰.
        PreviewActivity()
    } else {
        LocalContext.current as Activity
    },
    startDestination: Destination = if (LocalInspectionMode.current || LocalContext.current is PreviewActivity) {
        // @Compose 디자인 프리뷰, 프리뷰 실행.
        object : Destination {
            override val routePattern: String = "preview"
            override val arguments: List<NamedNavArgument> = emptyList()
            override val deepLinks: List<NavDeepLink> = emptyList()
        }
    } else {
        // 앱 실행
        throw IllegalArgumentException("startDestination must be provided when not in preview mode.")
    }
): BaseNavigator {
    val navHostController = rememberNavController()
    return remember(activity) {
        BaseNavigator(activity, navHostController, startDestination)
    }
}