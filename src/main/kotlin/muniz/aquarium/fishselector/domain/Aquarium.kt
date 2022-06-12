package muniz.aquarium.fishselector.domain

import muniz.aquarium.fishselector.exception.PreConditionException

class Aquarium {

    var fishCentimeterAvaliable: Int = 0
    var fishes: MutableList<Shoal> = mutableListOf()
    val tank:Tank? = null
    val hardScape: HardScape? = null

    constructor(tank:Tank, hardScape: HardScape) {
        this.fishes = mutableListOf()
        this.fishCentimeterAvaliable =  tank.liter - hardScape.height()
    }

    constructor(fishCentimeterAvaliable : Int,fishes : MutableList<Fish> ) {
        this.fishes = fishes.map { Shoal(it, it.minNumber) }.toMutableList()
        this.fishCentimeterAvaliable = fishCentimeterAvaliable
    }

    fun addFish(fish : Fish, count : Int){
        if(count < fish.minNumber)
            throw PreConditionException("${fish.minNumber} é o minimo de peixes dessa especie que se deve colocar no aquario")

        val shoal = Shoal(fish, count)

        if(shoal.totalCentimeter > fishCentimeterAvaliable)
            throw PreConditionException("Não é possivel adicionar esses peixes pois o aquario não suporta essa quandidade")

        fishes.add(shoal)
        fishCentimeterAvaliable -= shoal.totalCentimeter
    }

    fun getTemperatureRange() : String {
        if(fishes.isEmpty())
            return ""

        val minTemperature = fishes.map { it.fish.minTemperature }.maxOrNull()
        val maxTemperature = fishes.map { it.fish.maxTemperature }.minOrNull()

        return "${minTemperature}Cº - ${maxTemperature}Cº"
    }

    fun getPHRange() : String {
        if(fishes.isEmpty())
            return ""

        val minPH = fishes.map { it.fish.getMinPH() }.maxOrNull()
        val maxPH = fishes.map { it.fish.getMaxPH() }.minOrNull()

        return if(minPH == maxPH)
            minPH.toString()
        else
        return "$minPH - $maxPH"
    }

    fun getDHRange() : String {
        if(fishes.isEmpty())
            return ""

        val minDH = fishes.map { it.fish.getMinDH() }.maxOrNull()
        val maxDH = fishes.map { it.fish.getMaxDH() }.minOrNull()

        val minDHName = fishes.map { it.fish }.filter { it.getMinDH() == minDH }
                           .map { it.dh }.first()
                           .filter { it.min == minDH }.first().name.lowercase();


        val maxDHName = fishes.map { it.fish }.filter { it.getMaxDH() == maxDH }
                            .map { it.dh }.first()
                            .filter { it.max == maxDH }.first().name.lowercase();

        return if (minDHName == maxDHName) minDHName!!  else "$minDHName até $maxDHName"
    }

    private fun totalFishesSize() : Int {
        return  fishes.map {it.totalCentimeter}.sum()
    }
}