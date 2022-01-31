package muniz.aquarium.fishselector.application

import kotlinx.coroutines.flow.Flow
import muniz.aquarium.fishselector.domain.Fish
import muniz.aquarium.fishselector.domain.HardScapeAnswer
import muniz.aquarium.fishselector.domain.HardScapeQuestion
import muniz.aquarium.fishselector.infra.repository.FishAggregateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class AquariumService {

    @Autowired
    private lateinit var repository : FishAggregateRepository

    fun addNextQuestion(answer : HardScapeAnswer?,previousQuestions :  MutableList<HardScapeAnswer?>) : MutableList<HardScapeAnswer?>{
        if(answer == null)
            return returnFirstQuestion()

        if(answer == null)
            throw IllegalStateException("Resposta não pode ser vazia")

        previousQuestions.add(answer)

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