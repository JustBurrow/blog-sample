# 화면 정보 객체화

## 동기

내비게이션은 어떤 화면으로 들어오는 이동과 나가는 이동의 2가지로 구성된다. 나가는 이동은 `Navigator`로 객체화 할 수 있지만, 들어오는 이동은 관리할 방법이 없는 상태다.
어떤 화면으로 이동하는 방법을 객체로 정의하면, `Navigator`와 내비게이션 그래프 구성에 제공할 수 있다.

## 비교

<table>
<thead>
<tr>
<th></th>
<th>Scope 제한(인자화)</th>
<th>화면 객체화</th>
</tr>
</thead>
<tbody>
<tr>
<th rowspan="2">내비게이터</th>
<td>

```kotlin
class FirstNavigator(
    private val navHostController: NavHostController
) {
    fun second() {
        navHostController.navigate("second")
    }

    fun third(param1: Int) {
        navHostController.navigate("third/$param1")
    }
}

class SecondNavigator(
    private val navHostController: NavHostController
) {
    fun third(param1: Int, param2: String) {
        navHostController.navigate("third/$param1?param2=$param2")
    }

    fun back() {
        navHostController.popBackStack()
    }
}

class ThirdNavigator(
    private val navHostController: NavHostController
) {
    fun back() {
        navHostController.popBackStack()
    }
}
```

</td>
<td>

```kotlin
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

class SecondNavigator(
    private val navHostController: NavHostController
) {
    companion object {
        const val routePatten = "second"

        fun route() = routePatten
    }

    fun third(param1: Int, param2: String) {
        navHostController.navigate(ThirdNavigator.route(param1, param2))
    }

    fun back() {
        navHostController.popBackStack()
    }
}

class ThirdNavigator(
    private val navHostController: NavHostController
) {
    companion object {
        const val routePatten = "third/{param1}?param2={param2}"
        val arguments: List<NamedNavArgument> = listOf(
            navArgument("param1") {
                nullable = false
                type = NavType.IntType
            },
            navArgument("param2") {
                nullable = true
                type = NavType.StringType
            }
        )

        fun route(param1: Int) = "third/$param1"

        fun route(param1: Int, param2: String) = "third/$param1?param2=$param2"
    }

    fun back() {
        navHostController.popBackStack()
    }
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
<th rowspan="2">내비게이션 그래프</th>
<td>

```kotlin
@Composable
fun Root(
    navHostController: NavHostController = rememberNavController()
) {
    NavigationTheme {
        NavHost(navHostController, "splash") {
            composable("splash") {
                SplashScreen(navigator = SplashNavigator(navHostController))
            }
            composable("first") {
                FirstScreen(navigator = FirstNavigator(navHostController))
            }
            composable("second") {
                SecondScreen(navigator = SecondNavigator(navHostController))
            }
            composable(
                route = "third/{param1}?param2={param2}",
                arguments = listOf(
                    navArgument("param1") {
                        nullable = false
                        type = NavType.IntType
                    },
                    navArgument("param2") {
                        nullable = true
                        type = NavType.StringType
                    }
                )
            ) {
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
    navHostController: NavHostController = rememberNavController()
) {
    NavigationTheme {
        NavHost(navHostController, SplashNavigator.route()) {
            composable(SplashNavigator.routePatten) {
                SplashScreen(navigator = SplashNavigator(navHostController))
            }
            composable(FirstNavigator.routePattern) {
                FirstScreen(navigator = FirstNavigator(navHostController))
            }
            composable(SecondNavigator.routePatten) {
                SecondScreen(navigator = SecondNavigator(navHostController))
            }
            composable(ThirdNavigator.routePatten, ThirdNavigator.arguments) {
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
</tr>
<tr>
<td colspan="2">

![내비게이션 그래프 차이점](doc/file/navgraph%20-%20diff.png)

</td>
</tr>
</tbody>
</table>