package muniz.aquarium.fishselector.dto

import muniz.aquarium.fishselector.domain.Fish

class FishDTO (val name : String,
               val minNumber : Int,
               val imageUrl : String) {

    companion object {
        fun fromDomain(fish : Fish) : FishDTO{
            return FishDTO(fish.name, fish.minNumber,fish.imageUrl)
        }
    }
}