import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Home(changeScreen: (Screen) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedButton(onClick = { changeScreen(Screen.BrowseQuestions) }) {
            Text("Browse Questions")
        }
        OutlinedButton(onClick = { changeScreen(Screen.CreateCustomTest) }) {
            Text("Create Test")
        }
    }
}