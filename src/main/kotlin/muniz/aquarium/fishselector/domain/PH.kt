package muniz.aquarium.fishselector.domain

import java.math.BigDecimal

class PH (name: String, min : BigDecimal, max : BigDecimal = BigDecimal.ZERO) {

    val displayRange : String = if(max.equals(BigDecimal.ZERO))
                                  min.toString()
                                else
                                 "${min.setScale(1)} - ${max.setScale(1)}"

}
