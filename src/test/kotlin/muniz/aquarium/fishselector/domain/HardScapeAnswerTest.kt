package muniz.aquarium.fishselector.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class HardScapeAnswerTest {

    @Test
    fun notKnowNumbers(){
        val answers = ArrayList<HardScapeAnswer>()
        var curentQuestion : HardScapeQuestion? = HardScapeQuestion.SUBSTRACT_KNOLEDGEMENT

        //SUBSTRACT_FRONT_HEIGHT_TEXT
        curentQuestion = nextQuestion(curentQuestion,false)
        answers.add(HardScapeAnswer(curentQuestion!!, 3))

        //SUBSTRACT_BACK_HEIGHT_TEXT
        curentQuestion = nextQuestion(curentQuestion)
        answers.add(HardScapeAnswer(curentQuestion!!, 5))

        //ROCK_EXISTENCE_TEXT
        curentQuestion = nextQuestion(curentQuestion)

        //ROCK_KNOLEDGEMENT_TEXT
        curentQuestion = nextQuestion(curentQuestion,true)

        //ROCK_NUMBER_TEXT
        curentQuestion = nextQuestion(curentQuestion, false)
        answers.add(HardScapeAnswer(curentQuestion!!, 4))

        //WOOD_EXISTENCE_TEXT
        curentQuestion = nextQuestion(curentQuestion)

        //WOOD_KNOLEDGEMENT_TEXT
        curentQuestion = nextQuestion(curentQuestion,true)

        //WOOD_NUMBER_TEXT
        curentQuestion = nextQuestion(curentQuestion,false)
        answers.add(HardScapeAnswer(curentQuestion!!, 2))

        val hardScape =  HardScape(answers, 80, 30)
        assertEquals(19 , hardScape.height())
    }

    @Test
    fun knowAllWeights(){
        val answers = ArrayList<HardScapeAnswer>()

        //SUBSTRACT_KNOLEDGEMENT
        var curentQuestion : HardScapeQuestion? = HardScapeQuestion.SUBSTRACT_KNOLEDGEMENT

        //SUBSTRACT_WEIGHT
        curentQuestion = nextQuestion(curentQuestion,true)
        answers.add(HardScapeAnswer(curentQuestion!!, 10))

        //ROCK_EXISTENCE
        curentQuestion = nextQuestion(curentQuestion, true)

        //ROCK_KNOLEDGEMENT
        curentQuestion = nextQuestion(curentQuestion,true)

        //ROCK_WEIGHT
        curentQuestion = nextQuestion(curentQuestion, true)
        answers.add(HardScapeAnswer(curentQuestion!!, 15))

        //WOOD_EXISTENCE
        curentQuestion = nextQuestion(curentQuestion)

        //WOOD_KNOLEDGEMENT
        curentQuestion = nextQuestion(curentQuestion,true)

        //WOOD_WEIGHT
        curentQuestion = nextQuestion(curentQuestion,true)

       answers.add(HardScapeAnswer(curentQuestion!!, 5))

        val hardScape =  HardScape(answers)
        assertEquals(30 , hardScape.height())
    }

    @Test
    fun justSubstrate(){
        val answers = ArrayList<HardScapeAnswer>()

        //SUBSTRACT_KNOLEDGEMENT
        var curentQuestion : HardScapeQuestion? = HardScapeQuestion.SUBSTRACT_KNOLEDGEMENT

        //SUBSTRACT_WEIGHT
        curentQuestion = nextQuestion(curentQuestion,true)
        answers.add(HardScapeAnswer(curentQuestion!!, 15))

        val hardScape =  HardScape(answers)
        assertEquals(15 , hardScape.height())

    }

    private fun assertQuestion(questionText: String, curentQuestion : HardScapeQuestion?){
        assertEquals(questionText, curentQuestion?.displayText)
    }

    private fun nextQuestion(curentQuestion : HardScapeQuestion?, yes : Boolean? = null): HardScapeQuestion? {
        val nextQuestion = curentQuestion?.getNext(yes)
        println(nextQuestion?.displayText)
        return nextQuestion
    }
}