package muniz.aquarium.fishselector.domain

import java.lang.IllegalStateException

class Aquarium (val tank:Tank, val hardScape: HardScape) {

    private val fishes  = mutableListOf<Shoal>()

    fun addFish(fish : Fish, count : Int){
        if(count < fish.minNumber)
            throw IllegalStateException("${fish.minNumber} é o minimo de peixes dessa especie que se deve colocar no aquario")

        val shoal = Shoal(fish, count)

        if(shoal.totalCentimeter > fishCentimeterAvaliable())
            throw IllegalStateException("Não é possivel adicionar esses peixes pois o aquario não suporta essa quandidade")

        fishes.add(shoal)
    }

    fun fishCentimeterAvaliable(): Int {
        return tank.realLiter - hardScape.height() - totalFishesSize()
    }

    fun getTemperatureRange() : String {
        if(fishes.isEmpty())
            return ""

        val minTemperature = fishes.map { it.fish.minTemperature }.maxOrNull()
        val maxTemperature = fishes.map { it.fish.maxTemperature }.minOrNull()

        return "$minTemperature Cº - $maxTemperature Cº"
    }

    fun getPHRange() : String {
        if(fishes.isEmpty())
            return ""

        val minPH = fishes.map { it.fish.getMinPH() }.maxOrNull()
        val maxPH = fishes.map { it.fish.getMaxPH() }.minOrNull()

        return "$minPH - $maxPH"
    }

    private fun totalFishesSize() : Int {
        return  fishes.map {it.totalCentimeter}.sum()
    }
}