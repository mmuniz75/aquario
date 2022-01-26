package muniz.aquarium.fishselector.domain

import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Column

class Fish (val id : Int,
            val name: String,
            val size:Int,
            @Transient var ph : List<PH> = mutableListOf(),
            @Transient var dh: List<DH> = mutableListOf(),
            @Column("maxtemperature") val maxTemperature:Int,
            @Column("mintemperature") val minTemperature:Int,
            @Column("minnumber") val minNumber:Int,
            @Column("widthtank") val widthTank:Int,
            @Column("lengthtank") val lengthTank:Int,
            @Transient var compatibility : List<Fish> = mutableListOf()
           ) {

    @PersistenceConstructor
    constructor(id : Int,
                name: String,
                size:Int,
                maxTemperature:Int,
                minTemperature:Int,
                minNumber:Int,
                widthTank:Int,
                lengthTank:Int
    ): this(id,name,size,mutableListOf(),mutableListOf(),maxTemperature,minTemperature,minNumber,widthTank,lengthTank,mutableListOf())

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