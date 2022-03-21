package muniz.aquarium.fishselector.domain

import org.springframework.data.relational.core.mapping.Column
import java.math.BigDecimal

data class PH (val id : Int,
          val name: String,
          @Column("minph") val min : BigDecimal,
          @Column("maxph") val max : BigDecimal) {


    val displayRange : String = if(min == max)
                                  min.toString()
                                else
                                 "${min.setScale(1)} - ${max.setScale(1)}"

}
