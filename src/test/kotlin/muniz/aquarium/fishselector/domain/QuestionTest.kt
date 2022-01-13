package muniz.aquarium.fishselector.domain

import org.junit.jupiter.api.Test

class QuestionTest {

    @Test
    fun allYesQuestions(){
        var curentQuestion : HardScapeQuestion? = HardScapeQuestion.SUBSTRACT_KNOLEDGEMENT

        while(curentQuestion !=null ){
            println(curentQuestion.displayText)
            curentQuestion = curentQuestion.getNext(true)
        }
    }
}