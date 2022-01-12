package muniz.aquarium.fishselector.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TankTest {

    @Test
    fun checkLiter(){
        val tank = Tank(80,30,45)
        Assertions.assertEquals(108,tank.liter)
    }

    @Test
    fun check3Thickness(){
        val tank = Tank(30,15,20)
        Assertions.assertEquals(GlassThicknessEnum.THREE,tank.glassThickness)
    }

    @Test
    fun check5Thickness(){
        val tank = Tank(46,24,30)
        Assertions.assertEquals(GlassThicknessEnum.FIVE,tank.glassThickness)
    }

    @Test
    fun check6Thickness(){
        val tank = Tank(80,30,45)
        Assertions.assertEquals(GlassThicknessEnum.SIX,tank.glassThickness)
    }

    @Test
    fun check8Thickness(){
        val tank = Tank(100,50,50)
        Assertions.assertEquals(GlassThicknessEnum.EIGHT,tank.glassThickness)
    }
}