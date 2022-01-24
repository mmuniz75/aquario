package muniz.aquarium.fishselector.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

class AquariumTest {

    @Test
    fun checkFishCentimer(){
        assertEquals(81, createAquarium().fishCentimeterAvaliable())
    }

    @Test
    fun checkFishCentimerOneShoal(){
        val aquarium = createAquarium()
        aquarium.addFish(createNeon(), 10)
        assertEquals(51, aquarium.fishCentimeterAvaliable())
    }

    @Test
    fun checkMaxAquarium(){
        val aquarium = createAquarium()
        try {
            aquarium.addFish(createNeon(), 100)
            fail("Não pode permitir mais que aquario suporta")
        }catch(ex : IllegalStateException){
            assertEquals("Não é possivel adicionar esses peixes pois o aquario não suporta essa quandidade", ex.message)
        }
    }

    @Test
    fun checkMinFishShoal(){
        val aquarium = createAquarium()
        try {
            aquarium.addFish(createNeon(), 9)
            fail("Não pode permitir menos peixes que o cardume minimo")
        }catch(ex : IllegalStateException){
            assertEquals("10 é o minimo de peixes dessa especie que se deve colocar no aquario", ex.message)
        }
    }

    @Test
    fun checkFishCentimerTwoShoal(){
        val aquarium = createAquarium()
        aquarium.addFish(createNeon(), 10)

        val tetra = Fish(id=2,name= "Tetra", size=5, maxTemperature=28, minTemperature=24, minNumber=6, widthTank=60, lengthTank=30)
        aquarium.addFish(tetra, 7)

        assertEquals(16, aquarium.fishCentimeterAvaliable())
    }

    private fun createNeon() : Fish {
        return Fish(id=1,name= "Neon", size=3, maxTemperature=28, minTemperature=24, minNumber=10, widthTank=60, lengthTank=30)
    }

    private fun createAquarium() : Aquarium{
        val tank = Tank(width = 80, length = 30, heigth = 45)
        val hardScape = HardScape(substractWeight = 10, rocksHeight = 6, woodWeight = 1)

        return Aquarium(tank,hardScape)
    }

}