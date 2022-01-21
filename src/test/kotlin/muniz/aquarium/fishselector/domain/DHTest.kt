package muniz.aquarium.fishselector.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class DHTest {

    @Test
    fun showVerySoft(){
        val dh = DH(1, "Muito mole",0,4)
          assertEquals("0-4", dh.displayRange)
    }

    @Test
    fun showSoft(){
        val dh = DH(2, "Mole",4,8)
        assertEquals("4-8", dh.displayRange)
    }

    @Test
    fun showMedium(){
        val dh = DH(3, "Media",8,12)
        assertEquals("8-12", dh.displayRange)
    }

    @Test
    fun showHard(){
        val dh = DH(4, "Dura",12,18)
        assertEquals("12-18", dh.displayRange)
    }

    @Test
    fun showVeryHard(){
        val dh = DH(5, "Muito dura",18,30)
        assertEquals("18-30", dh.displayRange)
    }
}