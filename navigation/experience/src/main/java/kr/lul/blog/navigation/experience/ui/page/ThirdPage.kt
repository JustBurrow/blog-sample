package kr.lul.blog.navigation.experience.ui.page

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kr.lul.blog.navigation.artifact.rememberBaseNavigator
import kr.lul.blog.navigation.experience.ui.component.CommonFeature
import kr.lul.blog.navigation.experience.ui.navigator.ThirdNavigator
import kr.lul.blog.navigation.experience.ui.theme.NavigationTheme
import kr.lul.blog.navigation.experience.viewmodel.ThirdViewModel
import java.util.UUID

/**
 * 디자인 : [3rd 페이지](https://www.figma.com/board/dmtCT6n0IvUF89DH0BmCeL?node-id=8-371&node-type=section)
 */
@Serializable
data class ThirdPageArgs(
    @SerialName("param1")
    val param1: Int,
    @SerialName("param2")
    val param2: String? = null
)

@Composable
fun ThirdPage(
    navigator: ThirdNavigator,
    args: ThirdPageArgs,
    viewModel: ThirdViewModel = hiltViewModel()
) {
    Log.v("ui", "#ThirdScreen args : navigator=$navigator, args=$args, viewModel=$viewModel")
    ThirdPageContent(navigator = navigator, args = args)
}

@Composable
private fun ThirdPageContent(
    navigator: ThirdNavigator,
    args: ThirdPageArgs
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
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

        Text("param1: ${args.param1}", modifier = Modifier.padding(8.dp), style = MaterialTheme.typography.titleLarge)
        Text(
            "param2: ${args.param2 ?: "(null)"}",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleLarge
        )

        Button(onClick = navigator::back, modifier = Modifier.padding(16.dp)) {
            Text(text = "Go Back", style = MaterialTheme.typography.bodyLarge)
        }

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

private class ThirdPageArgsProvider : PreviewParameterProvider<ThirdPageArgs> {
    override val values = sequenceOf(
        ThirdPageArgs(2, null),
        ThirdPageArgs(1, UUID.randomUUID().toString().take(8).uppercase())
    )
}

@Composable
@Preview(showSystemUi = true)
private fun PreviewThirdPageContent(@PreviewParameter(ThirdPageArgsProvider::class) args: ThirdPageArgs) {
    NavigationTheme {
        ThirdPageContent(
            navigator = ThirdNavigator(rememberBaseNavigator()),
            args = args
        )
    }
}