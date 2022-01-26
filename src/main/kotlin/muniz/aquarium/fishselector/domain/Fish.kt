package muniz.aquarium.fishselector.domain

import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Column
import java.math.BigDecimal

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
        val min = getMinPH().toString()
        val max = getMaxPH().toString()
        return if (min == max) min else "$min - $max"
    }

    fun getMinPH(): BigDecimal {
        return if (ph.isEmpty()) BigDecimal.ZERO else ph.minOf { it.min }
    }

    fun getMaxPH(): BigDecimal {
        return if (ph.isEmpty()) BigDecimal.ZERO else ph.maxOf { it.max }
    }

    fun getDHRangeDisplay(): String {
        val min = getMinDH().toString()
        val max = getMaxDH().toString()
        return if (min ==max) min else "$min-$max"
    }

    fun getMinDH(): Int {
        return if (dh.isEmpty()) 0 else dh.minOf { it.min }
    }

    fun getMaxDH(): Int {
        return if (dh.isEmpty()) 0 else dh.maxOf { it.max }
    }

}