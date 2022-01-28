package muniz.aquarium.fishselector.dto

import muniz.aquarium.fishselector.domain.HardScapeAnswer

class HardScapeQuestionRequest(val answer : HardScapeAnswer?, val previousQuestions :  MutableList<HardScapeAnswer?>?) {
}