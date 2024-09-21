package kr.lul.blog.navigation.abstraction.ui.navigator

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

interface Destination {
    val routePattern: String

    val arguments: List<NamedNavArgument>

    val deepLinks: List<NavDeepLink>
}