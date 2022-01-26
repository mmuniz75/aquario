package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.runBlocking
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
            fishes.collect { fish ->
               println("${fish.name} - PH(${fish.getPHRangeDisplay()})  - DH(${fish.getDHRangeDisplay()})")}
        }

    }

    @Test
    fun listFishByTank(){
        runBlocking {
            val fishes = repository.listFishByTank(50,30)
            fishes.collect { fish -> println(fish.name)}
        }

    }
}