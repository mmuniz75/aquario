package muniz.aquarium.fishselector.dto

import muniz.aquarium.fishselector.domain.Fish

class FishDTO (val id : Int,
               val name : String,
               val minNumber : Int,
               val temperatureRange : String,
               val phRange : String,
               val dhRange : String,
               val imageUrl : String) {

    companion object {
        fun fromDomain(fish : Fish) : FishDTO{
            return FishDTO(fish.id, fish.name, fish.minNumber, fish.getTemperatureRangeDisplay(), fish.getPHRangeDisplay(),fish.getDHRangeDisplay(), fish.imageUrl)
        }
    }
}