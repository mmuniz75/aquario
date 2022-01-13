package muniz.aquarium.fishselector.domain

enum class Question(val displayText : String) {

    SUBSTRACT_KNOLEDGEMENT("Você sabe quantos kilos de substrato tem no seu aquário ?") {
        override fun getNext(yes: Boolean) = if (yes) ROCK_EXISTENCE else SUBSTRACT_FRONT_HEIGHT
        override fun getNext() = throw IllegalCallerException()
    },
    SUBSTRACT_FRONT_HEIGHT("Quantos centimetros de substratos você tem na parte da frente do aquário ?") {
        override fun getNext(yes: Boolean) = throw IllegalCallerException()
        override fun getNext() = SUBSTRACT_BACK_HEIGHT
    },
    SUBSTRACT_BACK_HEIGHT("Quantos centimetros de substratos você tem na parte da trás do aquário ?") {
        override fun getNext(yes: Boolean) = throw IllegalCallerException()
        override fun getNext() = ROCK_EXISTENCE
    },

    ROCK_EXISTENCE("Você possue rochas grandes no seu aquário ?") {
        override fun getNext(yes: Boolean) = if (yes) ROCK_KNOLEDGEMENT else WOOD_EXISTENCE
        override fun getNext() = throw IllegalCallerException()
    },
    ROCK_KNOLEDGEMENT("Você sabe quantos kilos de rochas tem no seu aquário ?") {
        override fun getNext(yes: Boolean) =  if (yes) WOOD_EXISTENCE else ROCK_NUMBER
        override fun getNext() = throw IllegalCallerException()
    },

    ROCK_NUMBER("Quantas rochas você tem no seu aquário ?") {
        override fun getNext(yes: Boolean) = throw IllegalCallerException()
        override fun getNext() = WOOD_EXISTENCE
    },

    WOOD_EXISTENCE("Você possue troncos grandes no seu aquário ?") {
        override fun getNext(yes: Boolean) =  if (yes) WOOD_KNOLEDGEMENT else null
        override fun getNext() = throw IllegalCallerException()
    },
    WOOD_KNOLEDGEMENT("Você sabe quantos kilos de troncos tem no seu aquário ?") {
        override fun getNext(yes: Boolean) = if (yes) null else WOOD_NUMBER
        override fun getNext() = throw IllegalCallerException()
    },
    WOOD_NUMBER("Quantos troncos você tem no seu aquário ?") {
        override fun getNext(yes: Boolean) = null
        override fun getNext() = throw IllegalCallerException()
    }
    ;

    abstract fun getNext(yes: Boolean) : Question?
    abstract fun getNext() : Question?

}