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

    fun getPhRangeDisplay(): String {
        val min = if (ph.isEmpty()) "" else ph.minOf { it.min }.toString()
        val max = if (ph.isEmpty()) "" else ph.maxOf { it.max }.toString()

        return if (min ==max) min else "$min - $max"
    }


}