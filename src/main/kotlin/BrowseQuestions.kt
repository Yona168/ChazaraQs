import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.*
import kotlinx.coroutines.launch
import kotlin.math.sqrt

@Composable
fun BrowseQuestions() {
    var selectedMaseches: Maseches? by remember { mutableStateOf(null) }
    var questionsToShow: Map<Mishnah, Collection<Question>>? by remember { mutableStateOf(null) }
    val coroutineScope = rememberCoroutineScope()
    if (selectedMaseches == null) {
        val masechtos = Maseches.values()
        println(selectedMaseches)
        MasechtosButtons(squareLayout(masechtos.toList())) { maseches ->
            selectedMaseches = maseches
        }
    } else if (questionsToShow == null) {
        val database = createJunkDatabase()
        coroutineScope.launch {
            val questions = database.getQuestionsFor(selectedMaseches!!)
            questionsToShow = questions
        }
    } else {
        PrakimList(questionsToShow!!)
    }

}

@Composable
fun MasechtosButtons(layout: MutableList<MutableList<Maseches>>, onClick: (Maseches) -> Unit) {
    Centered {
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

}

@Composable
fun PrakimList(masechesQuestions: Map<Mishnah, Collection<Question>>) {
    val prakim = masechesQuestions.keys.map { it.perek }.toSet()
    var selectedPerek: Int? by remember { mutableStateOf(null) }
    if (selectedPerek == null) {
        Centered {
            Surface {
                Text("Choose a perek")
            }
            Row {
                prakim.forEach { perek ->
                    OutlinedButton(onClick = { selectedPerek = perek }) {
                        Text("$perek")
                    }
                }
            }
        }

    } else {
        ViewQuestions(masechesQuestions.filter { it.key.perek == selectedPerek })
    }

}

@Composable
fun ViewQuestions(mishnahs: Map<Mishnah, Collection<Question>>) {
    LeftColumn {
        mishnahs.forEach { (mishnah, questions) ->
            Text("Mishnah ${mishnah.number}")
            Spacer(modifier = Modifier.size(5.dp))
            Text(mishnah.overview)
            Spacer(modifier = Modifier.size(5.dp))
            questions.forEach { ViewQuestion(it) }
        }
    }
}

@Composable
fun ViewQuestion(question: Question) {
    Text(question.question)
    val answer = question.let { question ->
        when (question) {
            is ShortAnswerQuestion -> question.answer
            is OptionsQuestion -> question.options.let {
                val mutable = it.toMutableList()
                val answer =
                    if (question is ManyChoiceQuestion) question.answer else setOf((question.answer as Int))
                answer.forEach { answer ->
                    mutable[answer] += "<--- Correct!"
                }
                mutable
            }.joinToString("\n")
        }
    }
    Text(answer)
}

private fun <T> squareLayout(list: List<T>): MutableList<MutableList<T>> {
    val sides = sqrt(list.size.toDouble()).toInt()
    val iterator = list.iterator()
    val layout = mutableListOf<MutableList<T>>()
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
    return layout
}