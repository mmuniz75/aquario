package muniz.aquarium.fishselector.domain

import java.lang.IllegalStateException
import java.math.BigDecimal

class HardScape(val rocksCount : Int = 0,
                var rocksHeight : Int = 0,
                val woodCount : Int = 0,
                var woodWeight : Int = 0,
                var substractWeight : Int = 0,
                val substractFrontHeight : Int = 0,
                val substractBackHeight : Int = 0,
                val tankWidth : Int = 0,
                val tankLenght : Int = 0
                )
{
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
                         .toInt()

    }

    private fun calculateRocksWeight(){
        val ROCKS_WEIGHT_AVERAGE = BigDecimal("1.2")
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