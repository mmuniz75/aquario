package muniz.aquarium.fishselector.dto

class HardScapeAnswerRequest(val question: String?,
                             val answer : Any?,
                             val previousAnswers :  MutableList<HardScapeAnswerDTO?>?) {
}