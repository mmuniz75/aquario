package muniz.aquarium.fishselector.domain

import org.springframework.data.relational.core.mapping.Column

class Fish (val id : Int,
            val name: String,
            val size:Int,
            var ph : List<PH> = ArrayList(),
            val dh: List<DH> = ArrayList(),
            @Column("maxtemperature") val maxTemperature:Int,
            @Column("maxtemperature") val minTemperature:Int,
            @Column("maxtemperature") val minNumber:Int,
            @Column("maxtemperature") val widthTank:Int,
            @Column("lengthtank") val lengthTank:Int,
            val compatibility : List<Fish> = ArrayList()
           ) {

}