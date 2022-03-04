package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class PHRepositoryTest {

    @Autowired
    private lateinit var repository : PHRepository

    @Test
    fun listPHForFishAcid(){
        runBlocking {
            val phs = repository.findPHByFishId(1)
            phs.collect { ph -> assertTrue(listOf("6.2 - 6.4","6.6 - 6.8").contains(ph.displayRange))}
        }
    }

    @Test
    fun listPHForFishAcidNeutral() {
        runBlocking {
            val phs = repository.findPHByFishId(4)
            phs.collect { ph ->
                assertTrue(listOf("6.2 - 6.4", "6.6 - 6.8", "7.0").contains(ph.displayRange))
            }
        }
    }
}