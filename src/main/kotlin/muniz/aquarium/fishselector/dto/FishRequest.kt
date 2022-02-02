package muniz.aquarium.fishselector.dto

class FishRequest (val tankWidth : Int,
                   val tankLength : Int,
                   val centimetersAvailable :Int,
                   val currentFishIds : List<Int>) {
}