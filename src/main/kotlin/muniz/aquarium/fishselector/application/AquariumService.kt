package muniz.aquarium.fishselector.application

import muniz.aquarium.fishselector.domain.HardScapeQuestion

class AquariumService {


    fun getNextQuestion(question : HardScapeQuestion, answer : Any,previousQuestions :  MutableMap<HardScapeQuestion?, String>) : Map<HardScapeQuestion?, String>{
        val yes = if (answer is Boolean) answer as Boolean else null
        val nextQuestion = if (previousQuestions.isEmpty()) HardScapeQuestion.getFirstQuestion() else question.getNext(yes)
        previousQuestions[nextQuestion] = answer as String
        return previousQuestions

    }
}