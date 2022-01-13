package muniz.aquarium.fishselector.domain

import java.math.BigDecimal

enum class GlassThickness(val cm : BigDecimal){

    THREE(BigDecimal("0.3")),
    FIVE(BigDecimal("0.5")),
    SIX(BigDecimal("0.6")),
    EIGHT(BigDecimal("0.8"));

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