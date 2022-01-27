package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class FishAggregateRepositoryTest {

    @Autowired
    private lateinit var repository : FishAggregateRepository

    @Test
    fun listFish(){
        runBlocking {
            val fishes = repository.listFish()
            val fish = fishes.first()
            assertEquals("Neon",fish.name)
            assertEquals("6.2 - 6.8",fish.getPHRangeDisplay())
            assertEquals("0-8",fish.getDHRangeDisplay())
        }

    }

    @Test
    fun listFishByTank(){
        runBlocking {
            val fishes = repository.listFishByTank(60,30).toList()
            assertEquals(4 , fishes.size)
            assertEquals("Neon",fishes[0].name)
            assertEquals("Mato grosso",fishes[1].name)
            assertEquals("Tetra Negro",fishes[2].name)
            assertEquals("Ramirezi",fishes[3].name)
        }

    }
}