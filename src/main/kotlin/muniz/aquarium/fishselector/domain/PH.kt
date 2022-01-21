package muniz.aquarium.fishselector.domain

import org.springframework.data.relational.core.mapping.Column
import java.math.BigDecimal

class PH (val id : Int,
          val name: String,
          @Column("minph") val min : BigDecimal,
          @Column("maxph") val max : BigDecimal = BigDecimal.ZERO) {


    val displayRange : String = if(max.equals(BigDecimal.ZERO))
                                  min.toString()
                                else
                                 "${min.setScale(1)} - ${max.setScale(1)}"

}
