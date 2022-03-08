package muniz.aquarium.fishselector.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal

class Tank(val width: Int,
           val length: Int,
           val height: Int) {

    @JsonIgnore
    val liter : Int = width * length * height / 1000

    @JsonIgnore
    val glassThickness : GlassThickness = GlassThickness.thickness(liter)

    @JsonIgnore
    val realLiter = calculateRealLiter()

    private fun calculateRealLiter() : Int {
        val WATER_SURFACE_SPACE = BigDecimal.ONE

        val doubleThickness = glassThickness.cm.multiply(BigDecimal(2))
        val realWidth = BigDecimal(width).subtract(doubleThickness)
        val realLength = BigDecimal(length).subtract(doubleThickness)
        val realHeight = BigDecimal(height).subtract(glassThickness.cm)
                          .subtract(WATER_SURFACE_SPACE)

        return  realWidth.multiply(realLength).multiply(realHeight).divide(BigDecimal(1000)).toInt()
    }

}