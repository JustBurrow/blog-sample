package kr.lul.blog.navigation.abstraction.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.lul.blog.navigation.abstraction.ui.theme.NavigationTheme

@Composable
fun CommonFeature(
    modifier: Modifier = Modifier,
    onClickRestart: () -> Unit = {},
    onClickExit: () -> Unit = {},
    onClickReopen: () -> Unit = {},
    onClickSettings: () -> Unit = {},
    onClickOpenWeb: (String) -> Unit = {},
    onClickCall: (String) -> Unit = {}
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedButton(onClick = onClickRestart, modifier = Modifier.padding(8.dp)) {
            Text(text = "Restart", style = MaterialTheme.typography.bodyLarge)
        }
        OutlinedButton(onClick = onClickExit, modifier = Modifier.padding(8.dp)) {
            Text(text = "Exit", style = MaterialTheme.typography.bodyLarge)
        }
        OutlinedButton(onClick = onClickReopen, modifier = Modifier.padding(8.dp)) {
            Text(text = "Reopen", style = MaterialTheme.typography.bodyLarge)
        }
        OutlinedButton(onClick = onClickSettings, modifier = Modifier.padding(8.dp)) {
            Text(text = "Settings", style = MaterialTheme.typography.bodyLarge)
        }

        var url by remember { mutableStateOf("") }
        OutlinedTextField(
            value = url,
            onValueChange = { url = it },
            modifier = Modifier.padding(8.dp),
            label = { Text("URL") },
            placeholder = { Text(text = "example.com") },
            leadingIcon = { Text(text = "https://", modifier = Modifier.padding(start = 8.dp)) },
            trailingIcon = {
                IconButton(
                    onClick = { onClickOpenWeb("https://$url") },
                    enabled = url.isNotBlank()
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Open Web",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri, imeAction = ImeAction.Go),
            keyboardActions = KeyboardActions {
                if (url.isNotBlank()) {
                    onClickOpenWeb("https://$url")
                }
            }
        )

        var phoneNumber by remember { mutableStateOf("") }
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            modifier = Modifier.padding(8.dp),
            label = { Text("Phone number") },
            placeholder = { Text("012-3456-7890") },
            trailingIcon = {
                IconButton(
                    onClick = { onClickCall(phoneNumber) },
                    enabled = phoneNumber.isNotBlank()
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Call phone",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions {
                if (phoneNumber.isNotBlank()) {
                    onClickCall(phoneNumber)
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewCommonFeature() {
    NavigationTheme {
        CommonFeature()
    }
}