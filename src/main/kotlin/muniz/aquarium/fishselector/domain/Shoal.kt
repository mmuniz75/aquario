package muniz.aquarium.fishselector.domain

class Shoal(val fish : Fish, val count : Int) {

    val totalCentimeter = count * fish.size
}