import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


enum class Screen {
    Home,
    BrowseQuestions,
    CreateCustomTest
}

@Composable
fun App() {
    var currentScreen by mutableStateOf(Screen.Home)
    val changeScreen = { screen: Screen -> currentScreen = screen }
    MaterialTheme {
        Common(changeScreen) {
            when (currentScreen) {
                Screen.Home -> Home(changeScreen)
                Screen.BrowseQuestions -> BrowseQuestions()
                else -> Home(changeScreen)
            }
        }
    }
}

@Composable
fun Common(changeScreen: (Screen) -> Unit, content: @Composable () -> Unit) {
    Row(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.primary)) {
        Column(modifier = Modifier.background(MaterialTheme.colors.secondary).fillMaxHeight().width(50.dp)) {
            IconButton(onClick = { changeScreen(Screen.Home) }) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            }
        }
        content()
    }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
