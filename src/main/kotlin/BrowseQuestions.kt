import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.sqrt

@Composable
fun BrowseQuestions() {
    var showQuestionsFor: Maseches? by mutableStateOf(null)
    val coroutineScope = rememberCoroutineScope()
    Centered {
        if (showQuestionsFor == null) {
            val masechtos = Maseches.values()
            val sides = sqrt(masechtos.size.toDouble()).toInt()
            val iterator = masechtos.iterator()
            val layout = mutableListOf<MutableList<Maseches>>()
            var currentRow = 0
            var currentColumn = 0
            while (iterator.hasNext()) {
                if (currentColumn == 0) {
                    layout.add(mutableListOf())
                }
                val row = layout[currentRow]
                row.add(iterator.next())
                currentColumn++
                if (currentColumn == sides) {
                    currentColumn = 0
                    currentRow++
                }
            }
            MasechtosButtons(layout) { maseches -> showQuestionsFor = maseches }
        } else {
            val database = createJunkDatabase()
            coroutineScope.launch {
                val questions = database.getQuestionsFor(showQuestionsFor!!)
            }
        }
    }

}

@Composable
fun MasechtosButtons(layout: MutableList<MutableList<Maseches>>, onClick: (Maseches) -> Unit) {
    Column {
        layout.forEach { row ->
            Row {
                row.forEach { button ->
                    OutlinedButton(onClick = { onClick(button) }, modifier = Modifier.padding(5.dp)) {
                        Text(button.toString())
                    }
                }
            }
        }
    }

}