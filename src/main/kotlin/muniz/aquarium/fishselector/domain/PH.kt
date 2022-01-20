package muniz.aquarium.fishselector.domain

import java.math.BigDecimal

class PH (name: String, min : BigDecimal, max : BigDecimal) {

    val displayRange : String = "${min.setScale(1)} - ${max.setScale(1)}"

}
