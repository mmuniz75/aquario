package muniz.aquarium.fishselector.dto

class AddFishRequest (val fishId : Int,
                      val fishCount : Int,
                      val centimetersAvailable :Int,
                      val currentFishIds : List<Int>)
{
}