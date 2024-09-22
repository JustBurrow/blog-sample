# 추상화

## 동기

여러 화면에 대해 UI(`@Composable`)과 내비게이션 그래프 구성에 제공하는 정보는 공통적인 정보를 제공한다.
이를 추사화 하면 중복 코드를 제거할 수 있고 사용법 또한 단순화 할 수 있다.

## 비교

<table>
<thead>
<tr>
<th> </th>
<th>화면 정보 객체화</th>
<th>추상화</th>
</tr>
</thead>
<tbody>
<tr>
<th><code>Navigator</code> 추상화</th>
<td> </td>
<td>

```kotlin
interface Navigator {
    val destination: Destination

    fun back()

    fun restart()

    fun exit()

    fun reopen()

    fun settings()

    fun web(url: Uri)

    fun web(url: String) {
        web(Uri.parse(url))
    }

    fun call(phoneNumber: String) {
        call(Uri.parse("tel:$phoneNumber"))
    }

    fun call(phoneNumber: Uri)
}
```

</td>
</tr>
<tr>
<th><code>Navigator</code> 위임</th>
<td></td>
<td>

```kotlin
@Immutable
open class BaseNavigator(
    val activity: Activity,
    val navHostController: NavHostController,
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
```

</td>
</tr>
<tr>
<th><code>Destination</code> 추상화</th>
<td></td>
<td>

```kotlin
interface Destination {
    val routePattern: String

    val arguments: List<NamedNavArgument>

    val deepLinks: List<NavDeepLink>
}
```

</td>
</tr>


<tr>
<th rowspan="2"><code>Navigator</code> 구현</th>
<td>

```kotlin
@Immutable
class FirstNavigator(
    private val navHostController: NavHostController
) {
    companion object {
        const val routePattern = "first"

        fun route() = routePattern
    }

    fun second() {
        navHostController.navigate(SecondNavigator.route())
    }

    fun third(param1: Int) {
        navHostController.navigate(ThirdNavigator.route(param1))
    }
}
```

</td>
<td>

```kotlin
@Immutable
class FirstNavigator(
    private val base: BaseNavigator
) : Navigator by base {
    companion object : Destination {
        override val routePattern = "first"

        override val arguments: List<NamedNavArgument> = emptyList()

        override val deepLinks: List<NavDeepLink> = emptyList()

        fun route() = routePattern
    }

    fun second() {
        base.navHostController.navigate(SecondNavigator.route())
    }

    fun third(param1: Int) {
        base.navHostController.navigate(ThirdNavigator.route(param1))
    }

    override val destination: Destination = Companion
}
```

</td>
</tr>
<tr>
<td colspan="2">

![Navigator 차이점](doc/file/navigator%20-%20diff.png)

</td>
</tr>
<tr>
<th>내비게이션 그래프 헬퍼</th>
<td></td>
<td>

```kotlin
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
````

</td>
</tr>
<tr>
<th rowspan="2">내비게이션 그래프</th>
<td>

```kotlin
@Composable
fun Root(
    navHostController: NavHostController = rememberNavController()
) {
    NavigationTheme {
        NavHost(navHostController, SplashNavigator.route()) {
            composable(SplashNavigator.routePattern) {
                SplashScreen(navigator = SplashNavigator(navHostController))
            }
            composable(FirstNavigator.routePattern) {
                FirstScreen(navigator = FirstNavigator(navHostController))
            }
            composable(SecondNavigator.routePattern) {
                SecondScreen(navigator = SecondNavigator(navHostController))
            }
            composable(ThirdNavigator.routePattern, ThirdNavigator.arguments) {
                ThirdScreen(
                    navigator = ThirdNavigator(navHostController),
                    args = it.toRoute()
                )
            }
        }
    }
}
```

</td>
<td>

```kotlin
@Composable
fun Root(
    baseNavigator: BaseNavigator
) {
    NavigationTheme {
        NavHost(
            navController = baseNavigator.navHostController,
            startDestination = baseNavigator.destination.routePattern,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(SplashNavigator(baseNavigator)) { _, navigator ->
                SplashScreen(navigator)
            }
            composable(FirstNavigator(baseNavigator)) { _, navigator ->
                FirstScreen(navigator)
            }
            composable(SecondNavigator(baseNavigator)) { _, navigator ->
                SecondScreen(navigator)
            }
            composable(ThirdNavigator(baseNavigator)) { backStackEntry, navigator ->
                ThirdScreen(
                    navigator = navigator,
                    args = backStackEntry.toRoute()
                )
            }
        }
    }
}
```

</td>
</tr>
<tr>
<td colspan="2">

![내비게이션 그래프 차이점](doc/file/navgraph%20-%20diff.png)

</td>
</tr>
</tbody>
</table>