package muniz.aquarium.fishselector.domain

import muniz.aquarium.fishselector.application.AquariumService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AquariumServiceTest {

    @Test
    fun allTrueQuestions(){
      val service = AquariumService()
      var questions = service.addNextQuestion(null, null)

      while(questions[questions.lastIndex] !=null){
        val lastQuestion = questions[questions.lastIndex]
        println(lastQuestion?.hardScapeQuestion?.displayText)
        val answer = if (lastQuestion?.hardScapeQuestion?.isYesOrNoQuestion!!) true else 10
        questions = service.addNextQuestion(HardScapeAnswer(lastQuestion.hardScapeQuestion,answer), questions)
      }

        val answers : List<HardScapeAnswer?> =  questions.toList()
        val hardScape  =  HardScape(answers.filterNotNull())
        Assertions.assertEquals(30, hardScape.height())
    }
}