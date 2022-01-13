package muniz.aquarium.fishselector.domain

import java.util.*


enum class HardScapeQuestion(val displayText : String,
                             val isYesOrNoQuestion : Boolean = false) {

    SUBSTRACT_KNOLEDGEMENT("Você sabe quantos kilos de substrato tem no seu aquário ?", true) {
        override fun getNext(knowSubstract: Any?)  = if (Objects.nonNull(knowSubstract) &&
                                                          knowSubstract as Boolean) SUBSTRACT_WEIGHT else SUBSTRACT_FRONT_HEIGHT
    },
    SUBSTRACT_WEIGHT("Quantos kilos de substrato tem no seu aquário ?") {
        override fun getNext(substractWeight: Any?) =ROCK_EXISTENCE
    },
    SUBSTRACT_FRONT_HEIGHT("Quantos centimetros de substratos você tem na parte da frente do aquário ?") {
        override fun getNext(frontHeight: Any?) = SUBSTRACT_BACK_HEIGHT
    },
    SUBSTRACT_BACK_HEIGHT("Quantos centimetros de substratos você tem na parte da trás do aquário ?") {
        override fun getNext(backHeight: Any?) = ROCK_EXISTENCE
    },

    ROCK_EXISTENCE("Você possue rochas grandes no seu aquário ?", true) {
        override fun getNext(hasRocks: Any?) = if (Objects.nonNull(hasRocks) &&
                                                   hasRocks as Boolean) ROCK_KNOLEDGEMENT else WOOD_EXISTENCE
    },
    ROCK_KNOLEDGEMENT("Você sabe quantos kilos de rochas tem no seu aquário ?", true) {
        override fun getNext(knowRocks: Any?) = if (Objects.nonNull(knowRocks) &&
                                                    knowRocks as Boolean) ROCK_WEIGHT else ROCK_NUMBER
    },
    ROCK_WEIGHT("Quantas kilos de rochas você tem no seu aquário ?") {
        override fun getNext(rocksWeight: Any?) = WOOD_EXISTENCE
    },
    ROCK_NUMBER("Quantas rochas você tem no seu aquário ?") {
        override fun getNext(knowCount: Any?) = WOOD_EXISTENCE
    },

    WOOD_EXISTENCE("Você possue troncos grandes no seu aquário ?", true) {
        override fun getNext(hasWood: Any?) = if (Objects.nonNull(hasWood) && hasWood as Boolean) WOOD_KNOLEDGEMENT else null
    },
    WOOD_KNOLEDGEMENT("Você sabe quantos kilos de troncos tem no seu aquário ?", true) {
        override fun getNext(knowWood: Any?) = if (Objects.nonNull(knowWood) && knowWood as Boolean) WOOD_WEIGHT else WOOD_NUMBER
    },
    WOOD_WEIGHT("Quantos kilos de troncos tem no seu aquário ?") {
        override fun getNext(knowWood: Any?) = null
    },
    WOOD_NUMBER("Quantos troncos você tem no seu aquário ?") {
        override fun getNext(woodCount: Any?) = null
    }
    ;

    abstract fun getNext(value: Any? = null) : HardScapeQuestion?

}