package muniz.aquarium.fishselector.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TankTest {

    @Test
    fun checkLiter(){
        val tank = Tank(80,30,45)
        assertEquals(108,tank.liter)
    }

    @Test
    fun checkRealLiter(){
        val tank = Tank(80,30,45)
        assertEquals(91,tank.realLiter)
    }

    @Test
    fun checkLiter2(){
        val tank = Tank(46,24,30)
        assertEquals(33,tank.liter)
    }

    @Test
    fun checkRealLiter2(){
        val tank = Tank(46,24,30)
        assertEquals(28,tank.realLiter)
    }

    @Test
    fun check3Thickness(){
        val tank = Tank(30,15,20)
        assertEquals(GlassThickness.THREE,tank.glassThickness)
    }

    @Test
    fun check5Thickness(){
        val tank = Tank(46,24,30)
        assertEquals(GlassThickness.FIVE,tank.glassThickness)
    }

    @Test
    fun check6Thickness(){
        val tank = Tank(80,30,45)
        assertEquals(GlassThickness.SIX,tank.glassThickness)
    }

    @Test
    fun check8Thickness(){
        val tank = Tank(100,50,50)
        assertEquals(GlassThickness.EIGHT,tank.glassThickness)
    }





}