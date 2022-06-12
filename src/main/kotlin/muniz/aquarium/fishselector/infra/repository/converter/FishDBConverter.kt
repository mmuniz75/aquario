package muniz.aquarium.fishselector.infra.repository.converter

import io.r2dbc.spi.Row
import muniz.aquarium.fishselector.domain.DH
import muniz.aquarium.fishselector.domain.Fish
import muniz.aquarium.fishselector.domain.PH
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import java.lang.IllegalArgumentException
import java.math.BigDecimal

@ReadingConverter
class FishDBConverter : Converter<Row?, Fish?> {

    override fun convert(source: Row): Fish {
        return Fish(
                    id = source.get("id", Integer::class.java)?.toInt()!!,
                    name = source.get("name", String::class.java)!!,
                    size = source.get("size", Integer::class.java)?.toInt()!!,
                    imageUrl = source.get("imageurl", String::class.java)!!,
                    maxTemperature = source.get("maxtemperature", Integer::class.java)?.toInt()!!,
                    minTemperature = source.get("mintemperature", Integer::class.java)?.toInt()!!,
                    minNumber = source.get("minnumber", Integer::class.java)?.toInt()!!,
                    widthTank = source.get("widthtank", Integer::class.java)?.toInt()!!,
                    lengthTank = source.get("lengthtank", Integer::class.java)?.toInt()!!,
                    initialSpace = source.get("initialSpace", Integer::class.java)?.toInt()!!,
                    ph = getPHs(source),
                    dh = getDHs(source)
               )
    }

    private fun getPHs(source: Row) : MutableList<PH>{
        return  try {
                    mutableListOf(
                        PH( id = 0, name = source.get("nameph", String::class.java)!!,
                            max = source.get("maxph", BigDecimal::class.java)!!,
                            min = source.get("minph", BigDecimal::class.java)!!)
                    )
                }catch(ie : IllegalArgumentException) {
                    mutableListOf()
                }
    }

    private fun getDHs(source: Row) : MutableList<DH>{
        return  try {
            mutableListOf(
                DH( id = 0, name = source.get("namedh", String::class.java)!!,
                    max=source.get("maxdh", Integer::class.java)?.toInt()!!,
                    min=source.get("mindh", Integer::class.java)?.toInt()!! )
            )
        }catch(ie : IllegalArgumentException) {
            mutableListOf()
        }
    }
}