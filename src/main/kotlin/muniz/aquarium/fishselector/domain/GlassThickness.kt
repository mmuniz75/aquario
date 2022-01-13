package muniz.aquarium.fishselector.domain

enum class GlassThickness{

    THREE,
    FIVE,
    SIX,
    EIGHT;

    companion object {
        fun thickness(liter : Int) : GlassThickness {
            return when {
                liter <= 30 -> THREE
                liter in 31..100 -> FIVE
                liter in 100..200 -> return SIX
                else -> EIGHT
            }
        }
    }

}