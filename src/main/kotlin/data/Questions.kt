package data

interface Question{
    val question:String
    val answer:Any
}
typealias Option=String
interface OptionsQuestion:Question{
    val options:List<Option>
}
interface MultipleChoiceQuestion:OptionsQuestion{
    override val answer:Int
}
interface ManyChoiceQuestion:OptionsQuestion {
    override val answer:Set<Int>
}
interface ShortAnswerQuestion:Question{
    override val answer: String
}
