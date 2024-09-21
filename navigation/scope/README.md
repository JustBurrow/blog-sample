# Scope 제한(인자화)

네비게이션을 인스턴스화 해서 전역함수인 확장함수를 특정 화면에서만 사용할 수 있도록 제한한다.

## 동기

내비게이션은 `@Composable` 함수 에서 다른 화면으로 이동하는 방식으로 이뤄진다. 따라서 화면의 `@Composable` 함수 인지로 사용할 수 있는 이동을 오브젝트로 묶어서 전달할 수 있다.
이렇게 하면

1. 화면 단위로 사용 가능한 이동을 제한할 수 있다.
2. 같은 화면으로 이동하더라도 서로 다른 이동 방식(인자)을 사용할 수 있다.

## 비교

<table>
<thead>
<tr>
<th></th>
<th>가이드</th>
<th>스코프 제한(인자화)</th>
</tr>
</thead>
<tbody>
<tr>
<th rowspan="2"><code>@Composable</code></th>
<td>

```kotlin
@Composable
fun Screen(
    onClickNext: (Int, String) -> Unit = { _, _ -> },
    onClickBack: () -> Unit = {}
) {
    Column {
        Row {
            var param1 by remember { mutableStateOf("") }
            var param2 by remember { mutableStateOf("") }
            Column {
                OutlinedTextField(
                    value = param1,
                    onValueChange = { param1 = it }
                )
                OutlinedTextField(
                    value = param2,
                    onValueChange = { param2 = it },
                    keyboardActions = KeyboardActions {
                        if (param1.isNotEmpty() && param1.isDigitsOnly() && param2.isNotEmpty()) {
                            onClickNext(param1.toInt(), param2)
                        }
                    }
                )
            }

            IconButton(
                onClick = { onClickNext(param1.toInt(), param2) }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Go to Next"
                )
            }
        }

        Button(onClick = onClickBack) {
            Text(text = "Go Back")
        }
    }
}
```

</td>
<td>

```kotlin
@Composable
fun Screen(
    navigator: ScreenNavigator
) {
    Column {
        Row {
            var param1 by remember { mutableStateOf("") }
            var param2 by remember { mutableStateOf("") }
            Column {
                OutlinedTextField(
                    value = param1,
                    onValueChange = { param1 = it }
                )
                OutlinedTextField(
                    value = param2,
                    onValueChange = { param2 = it },
                    keyboardActions = KeyboardActions {
                        if (param1.isNotEmpty() && param1.isDigitsOnly() && param2.isNotEmpty()) {
                            navigator.next(param1.toInt(), param2)
                        }
                    }
                )
            }

            IconButton(
                onClick = { navigator.next(param1.toInt(), param2) }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Go to Next"
                )
            }
        }

        Button(onClick = navigator::back) {
            Text(text = "Go Back")
        }
    }
}
```

</td>
</tr>
<tr>
<td colspan="2">

![@Composable 차이](doc/file/composable%20diff.png)

</td>
</tr>

<tr>
<th>백스택 조작</th>
<td>

```kotlin
fun NavController.next() {
    navigate("next")
}
```

</td>
<td>

```kotlin
class ScreenNavigator(
    private val navHostController: NavHostController
) {
    fun next() {
        navHostController.navigate("screen")
    }

    fun back() {
        navHostController.popBackStack()
    }
}
```

</td>
</tr>
<tr>
<th rowspan="2">내비게이션 그래프 구성</th>
<td>

```kotlin
@Composable
fun Root(
    navHostController: NavHostController = rememberNavController()
) {
    NavigationTheme {
        NavHost(navHostController, "screen") {
            composable("screen") {
                Screen(
                    onClickNext = navHostController::next,
                    onClickBack = navHostController::popBackStack
                )
            }
            composable("next") {
                // ...
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
        NavHost(navHostController, "screen") {
            composable("screen") {
                SecondScreen(navigator = ScreenNavigator(navHostController))
            }
            composable("next") {
                // ...
            }
        }
    }
}
```

</td>
</tr>
<tr>
<td colspan="2">

![내비게이션 그래프 차이](doc/file/navgraph%20-%20diff.png)

</td>
</tr>
</tbody>
</table>