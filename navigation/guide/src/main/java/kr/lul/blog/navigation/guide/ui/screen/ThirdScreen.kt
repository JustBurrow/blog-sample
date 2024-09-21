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
import kr.lul.blog.navigation.guide.viewmodel.ThirdViewModel

@Composable
fun ThirdScreen(
    viewModel: ThirdViewModel = hiltViewModel(),
    onClickBack: () -> Unit = {}
) {
    ThirdScreenContent(onClickBack = onClickBack)
}

@Composable
private fun ThirdScreenContent(
    onClickBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "3rd Screen",
            modifier = Modifier
                .background(Color.Blue)
                .padding(16.dp),
            color = Color.White,
            style = MaterialTheme.typography.displayLarge
        )

        Button(onClick = onClickBack, modifier = Modifier.padding(16.dp)) {
            Text(text = "Go Back", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun PreviewThirdScreenContent() {
    NavigationTheme {
        ThirdScreenContent()
    }
}