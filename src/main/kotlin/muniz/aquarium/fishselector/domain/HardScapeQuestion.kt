package muniz.aquarium.fishselector.domain

import com.fasterxml.jackson.annotation.JsonFormat


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class HardScapeQuestion(val displayText : String,
                             val isYesOrNoQuestion : Boolean = false) {

    SUBSTRACT_KNOLEDGEMENT(HardScapeQuestion.SUBSTRACT_KNOLEDGEMENT_TEXT, true) {
        override fun getNext(knowSubstract: Boolean?)  = if (knowSubstract!=null &&
                                                             knowSubstract) SUBSTRACT_WEIGHT else SUBSTRACT_FRONT_HEIGHT
    },
    SUBSTRACT_WEIGHT(HardScapeQuestion.SUBSTRACT_WEIGHT_TEXT) {
        override fun getNext(substractWeight: Boolean?) =ROCK_EXISTENCE
    },
    SUBSTRACT_FRONT_HEIGHT(HardScapeQuestion.SUBSTRACT_FRONT_HEIGHT_TEXT) {
        override fun getNext(frontHeight: Boolean?) = SUBSTRACT_BACK_HEIGHT
    },
    SUBSTRACT_BACK_HEIGHT(HardScapeQuestion.SUBSTRACT_BACK_HEIGHT_TEXT) {
        override fun getNext(backHeight: Boolean?) = ROCK_EXISTENCE
    },

    ROCK_EXISTENCE(HardScapeQuestion.ROCK_EXISTENCE_TEXT, true) {
        override fun getNext(hasRocks: Boolean?) = if (hasRocks !=null &&
                                                       hasRocks) ROCK_KNOLEDGEMENT else WOOD_EXISTENCE
    },
    ROCK_KNOLEDGEMENT(HardScapeQuestion.ROCK_KNOLEDGEMENT_TEXT, true) {
        override fun getNext(knowRocks: Boolean?) = if (knowRocks!=null &&
                                                        knowRocks) ROCK_WEIGHT else ROCK_COUNT
    },
    ROCK_WEIGHT(HardScapeQuestion.ROCK_WEIGHT_TEXT) {
        override fun getNext(rocksWeight: Boolean?) = WOOD_EXISTENCE
    },
    ROCK_COUNT(HardScapeQuestion.ROCK_NUMBER_TEXT) {
        override fun getNext(knowCount: Boolean?) = WOOD_EXISTENCE
    },

    WOOD_EXISTENCE(HardScapeQuestion.WOOD_EXISTENCE_TEXT, true) {
        override fun getNext(hasWood: Boolean?) = if (hasWood!=null &&
                                                      hasWood) WOOD_KNOLEDGEMENT else null
    },
    WOOD_KNOLEDGEMENT(HardScapeQuestion.WOOD_KNOLEDGEMENT_TEXT, true) {
        override fun getNext(knowWood: Boolean?) = if (knowWood!=null &&
                                                       knowWood) WOOD_WEIGHT else WOOD_COUNT
    },
    WOOD_WEIGHT(HardScapeQuestion.WOOD_WEIGHT_TEXT) {
        override fun getNext(knowWood: Boolean?) = null
    },
    WOOD_COUNT(HardScapeQuestion.WOOD_NUMBER_TEXT) {
        override fun getNext(woodCount: Boolean?) = null
    }
    ;

    companion object{
        const val SUBSTRACT_KNOLEDGEMENT_TEXT = "Voc?? sabe quantos kilos de substrato tem no seu aqu??rio ?"
        const val SUBSTRACT_WEIGHT_TEXT = "Quantos kilos de substrato tem no seu aqu??rio ?"
        const val SUBSTRACT_FRONT_HEIGHT_TEXT = "Quantos centimetros de substratos voc?? tem na parte da frente do aqu??rio ?"
        const val SUBSTRACT_BACK_HEIGHT_TEXT= "Quantos centimetros de substratos voc?? tem na parte da tr??s do aqu??rio ?"
        const val ROCK_EXISTENCE_TEXT = "Voc?? possue rochas grandes no seu aqu??rio ?"
        const val ROCK_KNOLEDGEMENT_TEXT = "Voc?? sabe quantos kilos de rochas tem no seu aqu??rio ?"
        const val ROCK_WEIGHT_TEXT = "Quantos kilos de rochas voc?? tem no seu aqu??rio ?"
        const val ROCK_NUMBER_TEXT = "Quantas rochas voc?? tem no seu aqu??rio ?"
        const val WOOD_EXISTENCE_TEXT = "Voc?? possue troncos grandes no seu aqu??rio ?"
        const val WOOD_KNOLEDGEMENT_TEXT = "Voc?? sabe quantos kilos de troncos tem no seu aqu??rio ?"
        const val WOOD_WEIGHT_TEXT = "Quantos kilos de troncos tem no seu aqu??rio ?"
        const val WOOD_NUMBER_TEXT = "Quantos troncos voc?? tem no seu aqu??rio ?"

        fun getFirstQuestion() = HardScapeQuestion.SUBSTRACT_KNOLEDGEMENT

        fun fromId(id: String?): HardScapeQuestion? {
            return  HardScapeQuestion.values().firstOrNull() { it.id == id }
        }
    }

    val id : String = name

    abstract fun getNext(yes: Boolean? = null) : HardScapeQuestion?



}