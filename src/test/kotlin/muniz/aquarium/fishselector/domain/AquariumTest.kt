package muniz.aquarium.fishselector.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AquariumTest {

    @Test
    fun checkFishCentimer(){
        val tank = Tank(width = 80, length = 30, heigth = 45)
        val hardScape = HardScape(substractWeight = 10, rocksHeight = 6, woodWeight = 1)
        Assertions.assertEquals(81, Aquarium(tank,hardScape).fishCentimerAvaliable())
    }
}