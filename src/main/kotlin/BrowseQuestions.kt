import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.Maseches
import data.createJunkDatabase
import kotlinx.coroutines.launch
import kotlin.math.sqrt

@Composable
fun BrowseQuestions() {
    Centered {
        val database = createJunkDatabase()
        val coroutineScope = rememberCoroutineScope()
        var masechtosState by remember { mutableStateOf(listOf<Maseches>()) }
        coroutineScope.launch {
            masechtosState = database.getAvailableMasechtos()
        }
        val sides = sqrt(masechtosState.size.toDouble()).toInt()
        val iterator = masechtosState.iterator()
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
        MasechtosButtons(layout)
    }

}

@Composable
fun MasechtosButtons(layout: MutableList<MutableList<Maseches>>) {
    Column {
        layout.forEach { row ->
            Row {
                row.forEach { button ->
                    OutlinedButton(onClick = {}, modifier= Modifier.padding(5.dp)) {
                        Text(button)
                    }
                }
            }
        }
    }

}