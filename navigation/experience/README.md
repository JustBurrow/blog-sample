# 개발자 경험

## 동기

개발자의 개발 경험을 개선하기 위한 변경.

## 비교

<table>
<thead>
<tr>
<th></th>
<th>추상화</th>
<th>개발자 경험</th>
</tr>
</thead>
<tbody>
<tr>
<th>

### [`BaseNavigator` 헬퍼](../artifact/src/main/java/kr/lul/blog/navigation/artifact/Compose.kt)

`Activity`, `@Preview`, 내비게이션 그래프 `@Composable`에서 `Navigator` 인스턴스 생성을 도와서 최소한의 코드로 내비게이션을 사용할 수 있도록 돕는다.

</th>
<td></td>
<td>

```kotlin
@Composable
fun rememberBaseNavigator(
    activity: Activity = if (LocalInspectionMode.current) {
        PreviewActivity()
    } else {
        LocalContext.current as Activity
    },
    startDestination: Destination = if (LocalInspectionMode.current || LocalContext.current is PreviewActivity) {
        object : Destination {
            override val routePattern: String = "preview"
            override val arguments: List<NamedNavArgument> = emptyList()
            override val deepLinks: List<NavDeepLink> = emptyList()
        }
    } else {
        throw IllegalArgumentException("startDestination must be provided when not in preview mode.")
    }
): BaseNavigator {
    val navHostController = rememberNavController()
    return remember(activity) {
        BaseNavigator(activity, navHostController, startDestination)
    }
}
```

</td>
</tr>

<tr>
<th>

### [`NavGraphBuilder.page()`](../artifact/src/main/java/kr/lul/blog/navigation/artifact/Compose.kt) 확장 함수

`Navigator` -> `Destination` -> `NavGraphBuilder.composable()` 구성 -> `@Composage page(navigator)` 순서의 함수 호출을 처리해서 최소한의
코드로 내비게이션을 사용할 수 있도록 돕는다.

</th>
<td></td>
<td>

```kotlin
inline fun <N : Navigator> NavGraphBuilder.page(
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
```

</td>
</tr>

<tr>
<th rowspan="2">

### `Activity`에서 `rememberBaseNavigator()` 사용

`MainActivity`에서 `@Composable`로 UI를 구성할 때도 사용.

</th>
<td>

```kotlin
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
```

</td>
<td>

```kotlin
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
```

</td>
</tr>
<tr>
<td colspan="2">

![MainActivity 차이점](doc/file/activity%20-%20diff.png)

</td>
</tr>

<tr>
<th rowspan="2">

### `rememberBaseNavigator`과 `NavGraphBuilder.page()` 사용

`baseNavigator` 인자를 `rememberBaseNavigator`로 생성해서 필요시 `@Preview`를 간단히 만들 수 있다.

내비게이션 구성 단위가 아토믹 디자인 시스템의 페이지 단위임을 명시적으로 표현한다.

</th>
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
<td>

```kotlin
@Composable
fun Root(
    baseNavigator: BaseNavigator = rememberBaseNavigator()
) {
    NavigationTheme {
        NavHost(
            navController = baseNavigator.navHostController,
            startDestination = baseNavigator.destination.routePattern,
            modifier = Modifier
                .fillMaxSize()
        ) {
            page(SplashNavigator(baseNavigator)) { _, navigator ->
                SplashPage(navigator)
            }
            page(FirstNavigator(baseNavigator)) { _, navigator ->
                FirstPage(navigator)
            }
            page(SecondNavigator(baseNavigator)) { _, navigator ->
                SecondPage(navigator)
            }
            page(ThirdNavigator(baseNavigator)) { backStackEntry, navigator ->
                ThirdPage(
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

<tr>
<th rowspan="2">

### `@Preview`에서 `rememberBaseNavigator` 사용

내비게이터 인스턴스 생성을 한번의 호출로 처리할 수 있다.

</th>
<td>

```kotlin
@Composable
@Preview(showSystemUi = true)
private fun PreviewFirstScreenContent() {
    NavigationTheme {
        FirstScreenContent(FirstNavigator(BaseNavigator(PreviewActivity(), rememberNavController(), FirstNavigator)))
    }
}
```

</td>
<td>

```kotlin
@Composable
@Preview(showSystemUi = true)
private fun PreviewFirstPageContent() {
    NavigationTheme {
        FirstPageContent(FirstNavigator(rememberBaseNavigator()))
    }
}
```

</td>
</tr>
<tr>
<td colspan="2">

![@Preview 차이점](doc/file/preview%20-%20diff.png)

</td>
</tr>
</tbody>
</table>