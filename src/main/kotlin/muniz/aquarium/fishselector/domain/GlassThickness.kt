package muniz.aquarium.fishselector.domain

import java.math.BigDecimal

enum class GlassThickness(val doubleCm : Int){

    THREE(1),
    FIVE(1),
    SIX(2),
    EIGHT(2);

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