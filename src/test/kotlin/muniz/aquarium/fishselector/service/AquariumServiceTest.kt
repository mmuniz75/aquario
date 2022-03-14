package muniz.aquarium.fishselector.service

import muniz.aquarium.fishselector.application.AquariumService
import muniz.aquarium.fishselector.domain.HardScape
import muniz.aquarium.fishselector.domain.HardScapeAnswer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AquariumServiceTest {

    @Test
    fun allTrueQuestions(){
        val hardScape  =  HardScape(createHardScape(true));
        Assertions.assertEquals(30, hardScape.height())
    }

    @Test
    fun allFalseQuestions(){
        val hardScape  =  HardScape(createHardScape(false),80,30);
        Assertions.assertEquals(24, hardScape.height())
    }

    fun createHardScape(yesForAll : Boolean): List<HardScapeAnswer> {
        val service = AquariumService()
        var questions = service.addNextQuestion(null, listOf())

        while(questions[questions.lastIndex] !=null){
            val lastQuestion = questions[questions.lastIndex]
            println(lastQuestion?.hardScapeQuestion?.displayText)
            val answer = if (lastQuestion?.hardScapeQuestion?.isYesOrNoQuestion!!) yesForAll else 10
            questions = service.addNextQuestion(HardScapeAnswer(lastQuestion.hardScapeQuestion,answer), questions.filterNotNull())
        }

        val answers =  questions
            .filterNotNull()
            .filter { !it.hardScapeQuestion.isYesOrNoQuestion &&  it.answer !=null }

        return answers
     }
}