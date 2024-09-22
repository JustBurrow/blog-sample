package kr.lul.blog.navigation.abstraction.ui.navigator

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

/**
 * 내비게이션 그래프에서 관리하는 화면.
 */
interface Destination {
    val routePattern: String

    val arguments: List<NamedNavArgument>

    val deepLinks: List<NavDeepLink>
}