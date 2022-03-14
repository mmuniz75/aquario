package muniz.aquarium.fishselector.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class HardScapeTest {

    @Test
    fun knowAllWeights(){
        val hardScape = HardScape(substractWeight = 10, woodWeight = 5, rocksHeight = 20)
        assertEquals(35, hardScape.height())
    }

    @Test
    fun checkRocksCalculation(){
        val hardScape = HardScape(substractWeight = 10, woodWeight = 5, rocksCount = 4)
        assertEquals(17, hardScape.height())
    }

    @Test
    fun checkWoodCalculation(){
        val hardScape = HardScape(substractWeight = 10, woodCount = 2, rocksHeight = 20)
        assertEquals(31, hardScape.height())
    }

    @Test
    fun checkSubstractCalculation(){
        val hardScape = HardScape(substractFrontHeight = 3, substractBackHeight = 5, tankWidth = 80,tankLenght = 30, woodWeight = 5, rocksHeight = 20)
        assertEquals(35, hardScape.height())
    }

}