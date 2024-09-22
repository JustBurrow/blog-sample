package kr.lul.blog.navigation.abstraction.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.PreviewActivity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import kr.lul.blog.navigation.abstraction.ui.component.CommonFeature
import kr.lul.blog.navigation.abstraction.ui.navigator.FirstNavigator
import kr.lul.blog.navigation.abstraction.ui.theme.NavigationTheme
import kr.lul.blog.navigation.abstraction.viewmodel.FirstViewModel

@Composable
fun FirstScreen(
    navigator: FirstNavigator,
    viewModel: FirstViewModel = hiltViewModel()
) {
    FirstScreenContent(
        navigator = navigator
    )
}

@Composable
private fun FirstScreenContent(
    navigator: FirstNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "1st",
            modifier = Modifier
                .background(Color.Red)
                .padding(16.dp),
            color = Color.White,
            style = MaterialTheme.typography.displayLarge
        )

        Button(onClick = navigator::second, modifier = Modifier.padding(16.dp)) {
            Text(text = "Go to 2nd", style = MaterialTheme.typography.bodyLarge)
        }


        var param1 by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = param1,
            onValueChange = { param1 = it },
            placeholder = { Text("Input param1, digits only.") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions {
                if (param1.isNotEmpty() && param1.isDigitsOnly()) {
                    navigator.third(param1.toInt())
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = { navigator.third(param1.toInt()) },
                    enabled = param1.isNotEmpty() && param1.isDigitsOnly(),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Go to 3rd",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )

        CommonFeature(
            modifier = Modifier.fillMaxWidth(),
            onClickRestart = navigator::restart,
            onClickExit = navigator::exit,
            onClickReopen = navigator::reopen,
            onClickSettings = navigator::settings,
            onClickOpenWeb = navigator::web,
            onClickCall = navigator::call
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun PreviewFirstScreenContent() {
    NavigationTheme {
        FirstScreenContent(FirstNavigator(PreviewActivity(), rememberNavController()))
    }
}