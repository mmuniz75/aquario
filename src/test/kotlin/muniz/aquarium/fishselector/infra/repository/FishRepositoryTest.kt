package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
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
            fishes.collect {assertTrue(listOf("Neon","Mato grosso","Tetra Negro","Barbus Ouro","Ramirezi","Colisa","Tricogaster","Kinguio").contains(it.name))}
        }

    }

    @Test
    fun findFishByID(){
        runBlocking {
            val fishes = repository.findByIdIn(listOf(2,3,4,7))
            fishes.collect {assertTrue(listOf("Mato grosso","Tetra Negro","Barbus Ouro","Tricogaster").contains(it.name))}
        }

    }

    @Test
    fun listFishByTank(){
        runBlocking {
            val fishes = repository.findByWidthTankLessThanEqualAndLengthTankLessThanEqual(60,30).toList()
            Assertions.assertEquals(4, fishes.size)
            Assertions.assertEquals("Neon", fishes[0].name)
            Assertions.assertEquals("Mato grosso", fishes[1].name)
            Assertions.assertEquals("Tetra Negro", fishes[2].name)
            Assertions.assertEquals("Ramirezi", fishes[3].name)
        }
    }


}