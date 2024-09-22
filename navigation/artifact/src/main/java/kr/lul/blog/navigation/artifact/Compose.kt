package kr.lul.blog.navigation.artifact

import android.app.Activity
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.PreviewActivity
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * @see androidx.navigation.compose.composable
 */
fun NavGraphBuilder.composable(
    destination: Destination,
    enterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    exitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    popEnterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    popExitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
    sizeTransform: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> SizeTransform?)? = null,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.routePattern,
        arguments = destination.arguments,
        deepLinks = destination.deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        sizeTransform = sizeTransform,
        content = content
    )
}

/**
 * 어떤 화면에서 사용할 수 있는 화면 이동([Navigator])에서 그 화면이 어떤 화면([Destination])인지 알아내서 내비게이션 그래프를 구성한다.
 *
 * @see androidx.navigation.compose.composable
 */
inline fun <N : Navigator> NavGraphBuilder.composable(
    navigator: N,
    noinline enterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    noinline exitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    noinline popEnterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    noinline popExitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
    noinline sizeTransform: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> SizeTransform?)? = null,
    crossinline content: @Composable AnimatedContentScope.(NavBackStackEntry, N) -> Unit
) {
    composable(
        route = navigator.destination.routePattern,
        arguments = navigator.destination.arguments,
        deepLinks = navigator.destination.deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        sizeTransform = sizeTransform
    ) {
        content(it, navigator)
    }
}

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