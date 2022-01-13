package muniz.aquarium.fishselector.domain

import org.junit.jupiter.api.Test
import java.util.*

class QuestionTest {

    @Test
    fun noInfo(){
        var curentQuestion : Question? = Question.SUBSTRACT_KNOLEDGEMENT

        while(curentQuestion !=null ){
            println(curentQuestion.displayText)
            curentQuestion = try {
                                curentQuestion.getNext()
                             }catch(ex: IllegalCallerException){
                                curentQuestion.getNext(true)
                             }
        }


    }
}