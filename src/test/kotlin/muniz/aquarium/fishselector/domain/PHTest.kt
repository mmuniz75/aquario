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

    @Test
    fun showNetralDisplay(){
        val ph = PH("Neutro",BigDecimal("7.0"))
        assertEquals("7.0", ph.displayRange)
    }

    @Test
    fun showAlcaliDisplay(){
        val ph = PH("Alcalino",BigDecimal("7.2"), BigDecimal("7.4"))
        assertEquals("7.2 - 7.4", ph.displayRange)
    }
}