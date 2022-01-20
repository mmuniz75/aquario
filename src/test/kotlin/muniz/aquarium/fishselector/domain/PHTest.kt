package muniz.aquarium.fishselector.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class PHTest {

    @Test
    fun showAcidDisplay(){
        val ph = PH("Acido",BigDecimal("6.2"), BigDecimal("6.8"))
          assertEquals("6.2 - 6.8", ph.displayRange)
    }
}