package muniz.aquarium.fishselector.dto

import muniz.aquarium.fishselector.domain.Tank

class AquariumSpaceRequest(val tank : Tank,
                           val answers : MutableList<HardScapeAnswerDTO?>?) {
}