package muniz.aquarium.fishselector.application

import kotlinx.coroutines.flow.Flow
import muniz.aquarium.fishselector.domain.*
import muniz.aquarium.fishselector.infra.repository.FishAggregateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class AquariumService {

    @Autowired
    private lateinit var repository : FishAggregateRepository

    fun addNextQuestion(answer : HardScapeAnswer?,previousQuestions :  List<HardScapeAnswer>) : List<HardScapeAnswer?>{
        if(answer == null)
            return returnFirstQuestion()

        if(answer == null)
            throw IllegalStateException("Resposta não pode ser vazia")

        val newPreviousList = mutableListOf<HardScapeAnswer?>()
        newPreviousList.addAll(previousQuestions)

        newPreviousList.add(answer)

        val nextQuestion =  if(answer.answer is Boolean)
                                 answer.hardScapeQuestion.getNext(answer.answer)
                            else
                                answer.hardScapeQuestion.getNext()

        if(nextQuestion!=null)
            newPreviousList.add(HardScapeAnswer(nextQuestion,null))
        else
            newPreviousList.add(null)

        return newPreviousList
    }

    fun returnFirstQuestion() : MutableList<HardScapeAnswer?>{
        return mutableListOf(HardScapeAnswer(HardScapeQuestion.getFirstQuestion(), null))
    }

    fun calculateAquariumAvaliableSpace(tank : Tank, answers : List<HardScapeAnswer>) : Int{
        val hardScape = HardScape(answers, tank.width, tank.length)
        val aquarium =  Aquarium(tank, hardScape)
        return aquarium.fishCentimeterAvaliable();
    }


}