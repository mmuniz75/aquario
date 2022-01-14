package muniz.aquarium.fishselector.domain

import org.junit.jupiter.api.Assertions
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
        var curentQuestion : HardScapeQuestion? = HardScapeQuestion.SUBSTRACT_KNOLEDGEMENT
        println(curentQuestion?.displayText)
        assertQuestion(HardScapeQuestion.SUBSTRACT_KNOLEDGEMENT_TEXT, curentQuestion)
        curentQuestion = nextQuestion(curentQuestion,true)

        assertQuestion(HardScapeQuestion.SUBSTRACT_WEIGHT_TEXT, curentQuestion)
        curentQuestion = nextQuestion(curentQuestion)

        assertQuestion(HardScapeQuestion.ROCK_EXISTENCE_TEXT, curentQuestion)
        curentQuestion = nextQuestion(curentQuestion,true)

        assertQuestion(HardScapeQuestion.ROCK_KNOLEDGEMENT_TEXT, curentQuestion)
        curentQuestion = nextQuestion(curentQuestion, true)

        assertQuestion(HardScapeQuestion.ROCK_WEIGHT_TEXT, curentQuestion)
        curentQuestion = nextQuestion(curentQuestion)

        assertQuestion(HardScapeQuestion.WOOD_EXISTENCE_TEXT, curentQuestion)
        curentQuestion = nextQuestion(curentQuestion,true)

        assertQuestion(HardScapeQuestion.WOOD_KNOLEDGEMENT_TEXT, curentQuestion)
        curentQuestion = nextQuestion(curentQuestion,true)

        assertQuestion(HardScapeQuestion.WOOD_WEIGHT_TEXT, curentQuestion)
        curentQuestion = nextQuestion(curentQuestion)

        assertNull(curentQuestion);
    }

    @Test
    fun justSubstrate(){
        var curentQuestion : HardScapeQuestion? = HardScapeQuestion.getFirstQuestion()
        println(curentQuestion?.displayText)
        assertQuestion(HardScapeQuestion.SUBSTRACT_KNOLEDGEMENT_TEXT, curentQuestion)
        curentQuestion = nextQuestion(curentQuestion,true)

        assertQuestion(HardScapeQuestion.SUBSTRACT_WEIGHT_TEXT, curentQuestion)
        curentQuestion = nextQuestion(curentQuestion)

        assertQuestion(HardScapeQuestion.ROCK_EXISTENCE_TEXT, curentQuestion)
        curentQuestion = nextQuestion(curentQuestion,false)

        assertQuestion(HardScapeQuestion.WOOD_EXISTENCE_TEXT, curentQuestion)
        curentQuestion = nextQuestion(curentQuestion,false)

        assertNull(curentQuestion);

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