package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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
        }
    }

    @Test
    fun findFishByID(){
        runBlocking {
            val fishes = repository.findByIdIn(listOf(2,3,7))
            fishes.collect {
                assertTrue(listOf("Mato grosso","Tetra Negro", "Tricogaster").contains(it.name))
                assertEquals("6.2 - 6.8",  it.getPHRangeDisplay())
                assertEquals("9-12",  it.getDHRangeDisplay())
            }
        }
    }

    @Test
    fun listFishByTank(){
        runBlocking {
            val fishes = repository.listFishByTank(60,30).toList()
            assertEquals(5 , fishes.size)
            assertEquals("Neon",fishes[0].name)
            assertEquals("Mato grosso",fishes[1].name)
            assertEquals("Tetra Negro",fishes[2].name)
            assertEquals("Ramirezi",fishes[3].name)
            assertEquals("Killifish",fishes[4].name)
        }
    }

    @Test
    fun listAllFish(){
        runBlocking {
            val fishes = repository.findByCompatibleFishEmptyTank(widthTank = 80, lengthTank = 40, aquariumRemainsSpace = 120).toList()
            fishes.map { println(it) }
            assertEquals(9 , fishes.size)
            assertEquals("Barbus Ouro",fishes[0].name)
            assertEquals("6.2 - 7.0",fishes[0].getPHRangeDisplay())
            assertEquals("9-12",fishes[0].getDHRangeDisplay())
            assertEquals("Tricogaster",fishes[8].name)
            assertEquals("6.2 - 6.8",fishes[8].getPHRangeDisplay())
            assertEquals("9-12",fishes[8].getDHRangeDisplay())
        }

    }

    @Test
    fun listNeonCompatibility(){
        runBlocking {
            val fishes = repository.findByCompatibleFish(widthTank = 80, lengthTank = 30, fishIds = mutableListOf(1),aquariumRemainsSpace = 50).toList()
            assertEquals(2 , fishes.size)
            assertEquals("Mato grosso",fishes[0].name)
            assertEquals("Tetra Negro",fishes[1].name)
        }

    }

    @Test
    fun listTetraMatoGrossoCompatibility(){
        runBlocking {
            val fishes = repository.findByCompatibleFish(widthTank = 80, lengthTank = 30, fishIds = mutableListOf(2,3),aquariumRemainsSpace = 50).toList()
            assertEquals(4 , fishes.size)
            assertEquals("Barbus Ouro",fishes[0].name)
            assertEquals("Colisa",fishes[1].name)
            assertEquals("Neon",fishes[2].name)
            assertEquals("Ramirezi",fishes[3].name)

        }
    }

    @Test
    fun listNeonTetraMatoGrossoCompatibility(){
        runBlocking {
            val fishes = repository.findByCompatibleFish(widthTank = 80, lengthTank = 30, fishIds = mutableListOf(1,2,3),aquariumRemainsSpace = 50).toList()
            assertEquals(0 , fishes.size)
        }

    }

    @Test
    fun listTricogasterCompatibility(){
        runBlocking {
            val fishes = repository.findByCompatibleFish(widthTank = 80, lengthTank = 30, fishIds = mutableListOf(7),aquariumRemainsSpace = 50).toList()
            assertEquals(3 , fishes.size)
            assertEquals("Barbus Ouro",fishes[0].name)
            assertEquals("Colisa",fishes[1].name)
            assertEquals("Ramirezi",fishes[2].name)
        }
    }

    @Test
    fun listMyAquariumCompatibility(){
        runBlocking {
            val fishes = repository.findByCompatibleFish(widthTank = 80, lengthTank = 30, fishIds = mutableListOf(2,3,4,5,6),aquariumRemainsSpace = 50).toList()
            assertEquals(0 , fishes.size)
        }
    }

}