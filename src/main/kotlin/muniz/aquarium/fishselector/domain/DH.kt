package muniz.aquarium.fishselector.domain

import org.springframework.data.relational.core.mapping.Column

data class DH(val id : Int,
         val name: String,
         @Column("mindh") val min : Int,
         @Column("maxdh") val max : Int) {
     val displayRange : String = "${min}-${max}"
}
