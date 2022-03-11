package data

import Maseches

sealed interface Question {
    val question: String
    val answer: Any
}
typealias Option = String

sealed interface OptionsQuestion : Question {
    val options: List<Option>
}

class MultipleChoiceQuestion(
    override val question: String,
    override val options: List<Option>,
    override val answer: Int
) : OptionsQuestion

class ManyChoiceQuestion(
    override val question: String,
    override val options: List<Option>,
    override val answer: Set<Int>
) : OptionsQuestion {
    constructor(question: String, options: List<Pair<Option, Boolean>>) :
            this(
                question = question,
                options = options.map { it.first },
                answer = options.mapIndexed { index, item -> index to item }.filter { it.second.second }
                    .map { it.first }.toSet()
            )
}

class ShortAnswerQuestion(
    override val question: String, override val answer: String
) : Question

data class Mishnah(val maseches: Maseches, val perek: Perek, val number: Int, val overview: String)

