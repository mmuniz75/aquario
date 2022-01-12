package muniz.aquarium.fishselector.domain

enum class Question(displayText : String) {

    SUBSTRACT_KNOLEDGEMENT("Você sabe quantos kilos de substrato tem no seu aquário ?"),
    SUBSTRACT_FRONT_HEIGHT("Quantos centimetros de substratos você tem na parte da frente do aquário ?"),
    SUBSTRACT_BACK_HEIGHT("Quantos centimetros de substratos você tem na parte da trás do aquário ?"),

    WOOD_EXISTENCE("Você possue troncos grandes no seu aquário ?"),
    WOOD_KNOLEDGEMENT("Você sabe quantos kilos de troncos tem no seu aquário ?"),
    WOOD_NUMBER("Quantos troncos você tem no seu aquário ?"),

    ROCK_EXISTENCE("Você possue rochas grandes no seu aquário ?"),
    ROCK_KNOLEDGEMENT("Você sabe quantos kilos de rochas tem no seu aquário ?"),
    ROCK_NUMBER("Quantas rochas você tem no seu aquário ?")

}