package muniz.aquarium.fishselector.service

import muniz.aquarium.fishselector.application.AquariumService
import muniz.aquarium.fishselector.domain.HardScape
import muniz.aquarium.fishselector.domain.HardScapeAnswer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AquariumServiceTest {

    @Test
    fun allTrueQuestions(){
        val hardScape  =  HardScape(createHardScepe(true));
        Assertions.assertEquals(30, hardScape.height())
    }

    @Test
    fun allFalseQuestions(){
        val hardScape  =  HardScape(createHardScepe(false),80,30);
        Assertions.assertEquals(36, hardScape.height())
    }

    fun createHardScepe(yesForAll : Boolean): List<HardScapeAnswer> {
        val service = AquariumService()
        var questions = service.addNextQuestion(null, null)

        while(questions[questions.lastIndex] !=null){
            val lastQuestion = questions[questions.lastIndex]
            println(lastQuestion?.hardScapeQuestion?.displayText)
            val answer = if (lastQuestion?.hardScapeQuestion?.isYesOrNoQuestion!!) yesForAll else 10
            questions = service.addNextQuestion(HardScapeAnswer(lastQuestion.hardScapeQuestion,answer), questions)
        }

        val answers : List<HardScapeAnswer?> =  questions.toList()
        return answers.filterNotNull()
     }
}