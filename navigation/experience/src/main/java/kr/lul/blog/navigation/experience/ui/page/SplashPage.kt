package kr.lul.blog.navigation.experience.ui.page

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.lul.blog.navigation.experience.ui.navigator.SplashNavigator
import kr.lul.blog.navigation.experience.ui.theme.NavigationTheme
import kr.lul.blog.navigation.experience.viewmodel.SplashViewModel

/**
 * 디자인 : [스플래시 페이지](https://www.figma.com/board/dmtCT6n0IvUF89DH0BmCeL)
 */
@Composable
fun SplashPage(
    navigator: SplashNavigator,
    viewModel: SplashViewModel = hiltViewModel()
) {
    Log.v("ui", "#SplashScreen args : navigator=$navigator, viewModel=$viewModel")
    val scope = rememberCoroutineScope()
    LaunchedEffect(viewModel) {
        scope.launch {
            delay(3000)
            navigator.first()
        }
    }
    SplashPageContent()
}

@Composable
private fun SplashPageContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Experience",
            modifier = Modifier
                .padding(16.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.displayLarge
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun PreviewSplashPageContent() {
    NavigationTheme {
        SplashPageContent()
    }
}