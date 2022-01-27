package muniz.aquarium.fishselector.application

import muniz.aquarium.fishselector.domain.HardScapeAnswer
import muniz.aquarium.fishselector.domain.HardScapeQuestion
import java.lang.IllegalStateException

class AquariumService {


    fun addNextQuestion(answer : HardScapeAnswer?,previousQuestions :  MutableList<HardScapeAnswer?>?) : MutableList<HardScapeAnswer?>{
        if(previousQuestions == null || previousQuestions.isEmpty())
            return returnFirstQuestion()

        if(answer == null)
            throw IllegalStateException("Resposta não pode ser vazia")

        previousQuestions.add(previousQuestions.size -1,answer)

        val nextQuestion =  if(answer.answer is Boolean)
                                 answer.hardScapeQuestion.getNext(answer.answer)
                            else
                                answer.hardScapeQuestion.getNext()

        if(nextQuestion!=null)
            previousQuestions.add(HardScapeAnswer(nextQuestion,null))
        else
            previousQuestions.add(null)

        return previousQuestions

    }

    fun returnFirstQuestion() : MutableList<HardScapeAnswer?>{
        return mutableListOf(HardScapeAnswer(HardScapeQuestion.getFirstQuestion(), null))
    }


}