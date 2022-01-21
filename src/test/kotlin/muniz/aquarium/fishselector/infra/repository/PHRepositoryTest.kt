package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class PHRepositoryTest {

    @Autowired
    private lateinit var repository : PHRepository

    @Test
    fun listPHForFish(){
        runBlocking {
            val phs = repository.findPHByFishId(1)
            phs.collect { ph -> println(ph.displayRange)}
        }

    }
}