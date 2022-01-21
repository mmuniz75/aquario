package muniz.aquarium.fishselector.domain

import org.springframework.data.relational.core.mapping.Column

class Fish (val id : Int,
            val name: String,
            val size:Int,
            var ph : List<PH> = ArrayList(),
            var dh: List<DH> = ArrayList(),
            @Column("maxtemperature") val maxTemperature:Int,
            @Column("maxtemperature") val minTemperature:Int,
            @Column("maxtemperature") val minNumber:Int,
            @Column("maxtemperature") val widthTank:Int,
            @Column("lengthtank") val lengthTank:Int,
            val compatibility : List<Fish> = ArrayList()
           ) {

    fun getPHRangeDisplay(): String {
        val min = if (ph.isEmpty()) "" else ph.minOf { it.min }.toString()
        val max = if (ph.isEmpty()) "" else ph.maxOf { it.max }.toString()
        return if (min ==max) min else "$min - $max"
    }

    fun getDHRangeDisplay(): String {
        val min = if (dh.isEmpty()) "" else dh.minOf { it.min }.toString()
        val max = if (dh.isEmpty()) "" else dh.maxOf { it.max }.toString()
        return if (min ==max) min else "$min-$max"
    }

}