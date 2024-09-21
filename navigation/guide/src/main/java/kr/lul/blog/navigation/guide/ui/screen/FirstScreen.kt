package kr.lul.blog.navigation.guide.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kr.lul.blog.navigation.guide.ui.theme.NavigationTheme
import kr.lul.blog.navigation.guide.viewmodel.FirstViewModel

@Composable
fun FirstScreen(
    viewModel: FirstViewModel = hiltViewModel(),
    onClickSecond: () -> Unit = {},
    onClickThird: () -> Unit = {}
) {
    FirstScreenContent(
        onClickSecond = onClickSecond,
        onClickThird = onClickThird
    )
}

@Composable
private fun FirstScreenContent(
    onClickSecond: () -> Unit = {},
    onClickThird: () -> Unit = {}
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

        Button(onClick = onClickSecond, modifier = Modifier.padding(16.dp)) {
            Text(text = "Go to 2nd", style = MaterialTheme.typography.bodyLarge)
        }
        Button(onClick = onClickThird, modifier = Modifier.padding(16.dp)) {
            Text(text = "Go to 3rd", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun PreviewFirstScreenContent() {
    NavigationTheme {
        FirstScreenContent()
    }
}