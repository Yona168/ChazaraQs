package data

typealias Perek=Int
typealias Maseches=String

interface Mishnah{
    val number: Int
    val maseches: Maseches
    val perek: Perek
    val overview:String
}

/**
 * Fetches question data from a database somewhere.
 */
interface Database {
    /**
     * Fetches [Question]'s for the given parameters. If [perek] AND [mishnah] are both null, all questions
     * for [maseches] will be fetched. If only [mishnah] is null, questions for the given [perek] in the [maseches]
     * will be fetched. If no parameters are null, questions for that specific mishnah will be fetched. If [mishnah]
     * is not null, but [perek] is, [perek] should default to 1.
     *
     * If the given input for a פרק or משנה are nonexistent, this method returns an empty collection
     *
     * @param[maseches] The מסכת to fetch questions for
     * @param[perek] The פרק within [maseches] to get questions for. null by default.
     * @param[mishnah] The משנה within [perek] to get questions for.
     */
    suspend fun getQuestionsFor(maseches: Maseches, perek:Perek, mishnah: Int? =null):Map<Mishnah, Collection<Question>>

    suspend fun getQuestionsFor(maseches: Maseches):Map<Mishnah, Collection<Question>>

    suspend fun getAvailableMasechtos():List<Maseches>


}

//used for testing
internal fun createJunkDatabase()=object:Database{
    override suspend fun getQuestionsFor(
        maseches: Maseches,
        perek: Perek,
        mishnah: Int?
    ): Map<Mishnah, Collection<Question>> {
        TODO("Not yet implemented")
    }

    override suspend fun getQuestionsFor(maseches: Maseches): Map<Mishnah, Collection<Question>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAvailableMasechtos()=listOf("Brachos","Demai","Kiddushin","Bava Kamma", "Bava Metzia","Bava Basra",
            "Pesachim","Shabbos","Eruvin","Beitza","Gittin","Yevamos")

}