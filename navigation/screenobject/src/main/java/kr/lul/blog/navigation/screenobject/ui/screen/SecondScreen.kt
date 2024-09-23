package kr.lul.blog.navigation.screenobject.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import kr.lul.blog.navigation.screenobject.ui.navigator.SecondNavigator
import kr.lul.blog.navigation.screenobject.ui.theme.NavigationTheme
import kr.lul.blog.navigation.screenobject.viewmodel.SecondViewModel

@Composable
fun SecondScreen(
    navigator: SecondNavigator,
    viewModel: SecondViewModel = hiltViewModel()
) {
    SecondScreenContent(navigator = navigator)
}

@Composable
private fun SecondScreenContent(
    navigator: SecondNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "2nd",
            modifier = Modifier
                .background(Color.Green)
                .padding(16.dp),
            color = Color.White,
            style = MaterialTheme.typography.displayLarge
        )

        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            var param1 by remember { mutableStateOf("") }
            var param2 by remember { mutableStateOf("") }
            Column {
                OutlinedTextField(
                    value = param1,
                    onValueChange = { param1 = it },
                    modifier = Modifier.padding(4.dp),
                    placeholder = { Text("Input param1, digits only.") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
                OutlinedTextField(
                    value = param2,
                    onValueChange = { param2 = it },
                    modifier = Modifier.padding(4.dp),
                    placeholder = { Text("Input param2.") },
                    keyboardOptions = if (param1.isNotEmpty() && param1.isDigitsOnly() && param2.isNotEmpty()) {
                        KeyboardOptions.Default.copy(imeAction = ImeAction.Go)
                    } else {
                        KeyboardOptions.Default
                    },
                    keyboardActions = KeyboardActions {
                        if (param1.isNotEmpty() && param1.isDigitsOnly() && param2.isNotEmpty()) {
                            navigator.third(param1.toInt(), param2)
                        }
                    }
                )
            }

            IconButton(
                onClick = { navigator.third(param1.toInt(), param2) },
                enabled = param1.isNotEmpty() && param1.isDigitsOnly() && param2.isNotEmpty(),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Go to 3rd",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Button(onClick = navigator::back, modifier = Modifier.padding(16.dp)) {
            Text(text = "Go Back", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun PreviewSecondScreenContent() {
    NavigationTheme {
        SecondScreenContent(SecondNavigator(rememberNavController()))
    }
}