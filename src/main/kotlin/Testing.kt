import data.*

//used for testing
internal fun createJunkDatabase() = object : Database {
    val questions = mapOf(
        Maseches.BRACHOS to mapOf(
            Mishnah(Maseches.BRACHOS, 2, 1, "This Mishnah is the 1st mishnah in the second perek of Brachos") to listOf(
                ManyChoiceQuestion(
                    "Pick all that are true", options = listOf(
                        "The sky is blue" to true,
                        "4 is a color" to false,
                        "Giraffes are really tall" to true
                    )
                ),
                ShortAnswerQuestion(
                    question = "What is this program supposed to do?",
                    answer = "Help with mishnah chazara"
                ),
                MultipleChoiceQuestion(
                    question = "Which of the following is false?",
                    options = listOf("1=1", "1+1=2", "4x5=21", "red is a color"),
                    3
                )
            ),
            Mishnah(Maseches.BRACHOS, 2, 4, "This is the 4th mishnah in the second perek of brachos") to listOf(
                ShortAnswerQuestion("What is the color of the sky", "Blue"),
                ManyChoiceQuestion(
                    "Which of the following are words?", options = listOf(
                        "Giraffe" to true,
                        "Braf" to false,
                        "Gerf" to false,
                        "Hat" to true
                    )
                )
            )
        ),
        Maseches.BAVA_KAMMA to mapOf(
            Mishnah(Maseches.BAVA_KAMMA, 1, 2, "This is the second mishnah in the first perek of bava Kamma") to listOf(
                ShortAnswerQuestion("What is the meaning of life?", "42"),
                MultipleChoiceQuestion("What is 10x5?", options = listOf("10", "20", "50", "70", "80"), 4)
            ),
            Mishnah(Maseches.BAVA_KAMMA, 1,3, "This is the third mishnah in the first perek of bava kamma") to listOf(
                ShortAnswerQuestion("What Maseches is this in?","BK"),
                ShortAnswerQuestion("How are you?", "Good")
            )
        )
    )

    override suspend fun getQuestionsFor(
        maseches: Maseches,
        perek: Perek,
        mishnah: Int?
    ): Map<Mishnah, Collection<Question>>? {
        val questionsForMaseches = questions[maseches]?.filter { it.key.perek == perek }
        return if (mishnah != null) {
            return questionsForMaseches?.filter { it.key.number == mishnah }
        } else questionsForMaseches
    }

    override suspend fun getQuestionsFor(maseches: Maseches) = questions[maseches]

    override suspend fun getAvailableMasechtos() = listOf(Maseches.BRACHOS)

}