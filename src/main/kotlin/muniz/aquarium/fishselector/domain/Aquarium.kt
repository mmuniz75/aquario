package muniz.aquarium.fishselector.domain

class Aquarium (val tank:Tank, val hardScape: HardScape) {

    private val fishes  : List<Fish> = ArrayList()

    fun fishCentimerAvaliable(): Int {
        return tank.realLiter - hardScape.height()
    }
}