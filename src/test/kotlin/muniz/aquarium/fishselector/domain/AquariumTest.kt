package muniz.aquarium.fishselector.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import java.math.BigDecimal

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
        aquarium.addFish(createTetra(), 7)

        assertEquals(16, aquarium.fishCentimeterAvaliable())
    }

    @Test
    fun checkTemperatureRange(){
        val aquarium = createAquarium()
        aquarium.addFish(createBarbus(), 6)
        aquarium.addFish(createColdFish(), 1)
        aquarium.addFish(createHotFish(), 1)

        assertEquals("24 Cº - 25 Cº", aquarium.getTemperatureRange())
    }

    @Test
    fun checkPHRangeAcid(){
        val aquarium = createAquarium()
        val fish1 = createColdFish()
        fish1.ph = listOf(createAcidPH())
        aquarium.addFish(fish1, 1)
        assertEquals("6.2 - 6.8", aquarium.getPHRange())
    }

    @Test
    fun checkPHRangeAcidNeutral(){
        val aquarium = createAquarium()
        val fish1 = createColdFish()
        fish1.ph = listOf(createAcidPH(),createNeutralPH())
        aquarium.addFish(fish1, 1)
        assertEquals("6.2 - 7.0", aquarium.getPHRange())
    }

    @Test
    fun checkPHRangeNeutralAlcali(){
        val aquarium = createAquarium()
        val fish1 = createColdFish()
        fish1.ph = listOf(createAlcaliPH(),createNeutralPH())
        aquarium.addFish(fish1, 1)
        assertEquals("7.0 - 7.4", aquarium.getPHRange())
    }

    @Test
    fun checkPHRangeFishAcidAndFishAcidNeutral(){
        val aquarium = createAquarium()
        val fish1 = createColdFish()
        fish1.ph = listOf(createAcidPH(),createNeutralPH())

        val fish2 = createColdFish()
        fish2.ph = listOf(createAcidPH())

        aquarium.addFish(fish1, 1)
        aquarium.addFish(fish2, 1)
        assertEquals("6.2 - 6.8", aquarium.getPHRange())
    }

    @Test
    fun checkDHRangeSoft(){
        val aquarium = createAquarium()
        val fish1 = createColdFish()
        fish1.dh = listOf(createDHSoft())
        aquarium.addFish(fish1, 1)
        assertEquals("4 - 8", aquarium.getDHRange())
    }


    @Test
    fun checkDHRangeFishAllAndFishMediunHard(){
        val aquarium = createAquarium()
        val fish1 = createColdFish()
        fish1.dh = listOf(createDHSoft(),createDHMedium(),createDHard())
        aquarium.addFish(fish1, 1)

        val fish2 = createHotFish()
        fish2.dh = listOf(createDHMedium(),createDHard())
        aquarium.addFish(fish2, 1)

        assertEquals("9 - 18", aquarium.getDHRange())
    }

    @Test
    fun checkDHRangeFishAllAndFishSoftMediun(){
        val aquarium = createAquarium()
        val fish1 = createColdFish()
        fish1.dh = listOf(createDHSoft(),createDHMedium(),createDHard())
        aquarium.addFish(fish1, 1)

        val fish2 = createHotFish()
        fish2.dh = listOf(createDHMedium(),createDHSoft())
        aquarium.addFish(fish2, 1)

        assertEquals("4 - 12", aquarium.getDHRange())
    }

    @Test
    fun checkDHRangeFishMixt(){
        val aquarium = createAquarium()
        val fish1 = createColdFish()
        fish1.dh = listOf(createDHSoft(),createDHMedium(),createDHard())
        aquarium.addFish(fish1, 1)

        val fish2 = createHotFish()
        fish2.dh = listOf(createDHMedium(),createDHard())
        aquarium.addFish(fish2, 1)

        val fish3 = createBarbus()
        fish3.dh = listOf(createDHMedium())
        aquarium.addFish(fish3, 6)

        assertEquals("9 - 12", aquarium.getDHRange())
    }

    private fun createAcidPH() : PH{
        return  PH(1 ,"Acido", BigDecimal("6.2"), BigDecimal("6.8"))
    }

    private fun createNeutralPH() : PH{
        return PH(2, "Neutro", BigDecimal("7.0"),BigDecimal("7.0"))
    }

    private fun createAlcaliPH() : PH{
        return PH(3, "Alcalino", BigDecimal("7.2"), BigDecimal("7.4"))
    }

    fun createDHSoft():DH{
        return DH(2, "Mole",4,8)
    }

    fun createDHMedium():DH{
        return DH(3, "Media",9,12)
    }

    fun createDHard():DH{
        return DH(4, "Dura",13,18)
    }

    private fun createNeon() : Fish {
        return Fish(id=1,name= "Neon", size=3, maxTemperature=30, minTemperature=24, minNumber=10, widthTank=60, lengthTank=30)
    }

    private fun createTetra() : Fish {
        return Fish(id=2,name= "Tetra", size=5, maxTemperature=30, minTemperature=20, minNumber=6, widthTank=60, lengthTank=30)
    }

    private fun createBarbus() : Fish {
        return Fish(id=3,name= "Barbus", size=5, maxTemperature=25, minTemperature=18, minNumber=6, widthTank=60, lengthTank=30)
    }

    private fun createColdFish() : Fish {
        return Fish(id=4,name= "Cold Fish", size=1, maxTemperature=25, minTemperature=18, minNumber=1, widthTank=60, lengthTank=30)
    }

    private fun createHotFish() : Fish {
        return Fish(id=4,name= "Cold Fish", size=1, maxTemperature=30, minTemperature=24, minNumber=1, widthTank=60, lengthTank=30)
    }

    private fun createAquarium() : Aquarium{
        val tank = Tank(width = 80, length = 30, height = 45)
        val hardScape = HardScape(substractWeight = 10, rocksHeight = 6, woodWeight = 1)
        return Aquarium(tank,hardScape)
    }

}