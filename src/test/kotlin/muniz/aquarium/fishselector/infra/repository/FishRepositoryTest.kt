package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class FishRepositoryTest {

    @Autowired
    private lateinit var repository : FishRepository

    @Test
    fun listFish(){
        val fishes = repository.findAll()
        runBlocking {
            fishes.collect { fish -> println(fish.name)}
        }

    }

    @Test
    fun listFishByTank(){
        runBlocking {
            val fishes = repository.findByWidthTankLessThanEqualAndLengthTankLessThanEqual(50,30)
            fishes.collect { fish -> println(fish.name)}
        }

    }

    @Test
    fun listFishByCompatibility(){
        runBlocking {
            val fishes = repository.findByCompatibleFish(widthTank = 80, lengthTank = 30, fishIds = mutableListOf(2),2,  aquariumRemainsSpace = 50)
            fishes.collect { fish -> println(fish.name)}
        }

    }
}