package muniz.aquarium.fishselector.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TankTest {

    @Test
    fun checkLiter(){
        val tank = Tank(80,30,45)
        Assertions.assertEquals(108,tank.liter)
    }
}