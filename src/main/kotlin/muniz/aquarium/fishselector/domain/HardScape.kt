package muniz.aquarium.fishselector.domain

import java.lang.IllegalStateException
import java.math.BigDecimal

class HardScape(var rocksCount : Int = 0,
                var rocksHeight : Int = 0,
                var woodCount : Int = 0,
                var woodWeight : Int = 0,
                var substractWeight : Int = 0,
                var substractFrontHeight : Int = 0,
                var substractBackHeight : Int = 0,
                var tankWidth : Int = 0,
                var tankLenght : Int = 0
                )
{

    constructor(questions : List<HardScapeAnswer>, tankWidth: Int = 0, tankLenght: Int = 0) :
            this( tankWidth = tankWidth,
                  tankLenght = tankLenght
                ) {

        rocksCount = getValueFromEnum(questions, HardScapeQuestion.ROCK_COUNT)
        rocksHeight = getValueFromEnum(questions, HardScapeQuestion.ROCK_WEIGHT)
        woodCount = getValueFromEnum(questions, HardScapeQuestion.WOOD_COUNT)
        woodWeight = getValueFromEnum(questions, HardScapeQuestion.WOOD_WEIGHT)
        substractWeight = getValueFromEnum(questions, HardScapeQuestion.SUBSTRACT_WEIGHT)
        substractFrontHeight = getValueFromEnum(questions, HardScapeQuestion.SUBSTRACT_FRONT_HEIGHT)
        substractBackHeight = getValueFromEnum(questions, HardScapeQuestion.SUBSTRACT_BACK_HEIGHT)
    }

    private fun getValueFromEnum(questions : List<HardScapeAnswer>, hardScapeQuestion: HardScapeQuestion) : Int{
        val value = questions.find { it.hardScapeQuestion == hardScapeQuestion }?.answer
        return if (value!=null) value as Int else 0
    }

    fun height() : Int{
        canculateSubstractWeight()
        calculateRocksWeight()
        calculateWoodWeight()
        return substractWeight + woodWeight + rocksHeight
    }

    private fun canculateSubstractWeight(){
        if(substractWeight>0) return;

        if(substractFrontHeight == 0) throw IllegalStateException("Altura frontal do substrato não informada")
        if(substractBackHeight  == 0) throw IllegalStateException("Altura traseira do substrato não informada")
        if(tankLenght == 0) throw IllegalStateException("Largura do aquario não informada")
        if(tankWidth == 0) throw IllegalStateException("Comprimento do aquario não informada")

        val substractAverage = BigDecimal(substractFrontHeight).add(BigDecimal(substractBackHeight)).divide(BigDecimal(2))

        substractWeight = BigDecimal(tankWidth)
                         .multiply(BigDecimal(tankLenght))
                         .multiply(substractAverage)
                         .multiply(BigDecimal("1.5"))
                         .divide(BigDecimal(1000))
                         .toInt()

    }

    private fun calculateRocksWeight(){
        val ROCKS_WEIGHT_AVERAGE = BigDecimal.ONE
        if(rocksHeight>0) return;
        if(rocksCount == 0) throw IllegalStateException("Numero de rochas não informado")
        rocksHeight = BigDecimal(rocksCount).multiply(ROCKS_WEIGHT_AVERAGE).toInt()
    }

    private fun calculateWoodWeight(){
        val WOOD_WEIGHT_AVERAGE = BigDecimal("0.5")
        if(woodWeight>0) return;
        if(woodCount == 0) throw IllegalStateException("Numero de troncos não informado")
        woodWeight = BigDecimal(woodCount).multiply(WOOD_WEIGHT_AVERAGE).toInt()
    }
}