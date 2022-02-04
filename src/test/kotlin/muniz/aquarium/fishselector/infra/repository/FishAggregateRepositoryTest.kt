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
            assertEquals("6.2 - 6.8",fish.getPHRangeDisplay())
            assertEquals("0-8",fish.getDHRangeDisplay())
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
            assertEquals(4 , fishes.size)
            assertEquals("Neon",fishes[0].name)
            assertEquals("Mato grosso",fishes[1].name)
            assertEquals("Tetra Negro",fishes[2].name)
            assertEquals("Ramirezi",fishes[3].name)
        }
    }

    /*
     1 - Neon
     2 - Mato grosso
     3 - Tetra Negro
     4 - Barbus Ouro
     5 - Ramirezi
     6 - Colisa
     7 - Tricogaster
    */
    @Test
    fun listNeonCompatibility(){
        runBlocking {
            val fishes = repository.findByCompatibleFish(widthTank = 80, lengthTank = 30, fishIds = mutableListOf(1),aquariumRemainsSpace = 50).toList()
            assertEquals(3 , fishes.size)
            assertEquals("Neon",fishes[0].name)
            assertEquals("Mato grosso",fishes[1].name)
            assertEquals("Tetra Negro",fishes[2].name)
        }

    }

    @Test
    fun listTetraMatoGrossoCompatibility(){
        runBlocking {
            val fishes = repository.findByCompatibleFish(widthTank = 80, lengthTank = 30, fishIds = mutableListOf(2,3),aquariumRemainsSpace = 50).toList()
            assertEquals(6 , fishes.size)
            assertEquals("Neon",fishes[0].name)
            assertEquals("Mato grosso",fishes[1].name)
            assertEquals("Tetra Negro",fishes[2].name)
            assertEquals("Barbus Ouro",fishes[3].name)
            assertEquals("Ramirezi",fishes[4].name)
            assertEquals("Colisa",fishes[5].name)
        }
    }

    @Test
    fun listNeonTetraMatoGrossoCompatibility(){
        runBlocking {
            val fishes = repository.findByCompatibleFish(widthTank = 80, lengthTank = 30, fishIds = mutableListOf(1,2,3),aquariumRemainsSpace = 50).toList()
            assertEquals(3 , fishes.size)
            assertEquals("Neon",fishes[0].name)
            assertEquals("Mato grosso",fishes[1].name)
            assertEquals("Tetra Negro",fishes[2].name)
        }

    }

    @Test
    fun listTricogasterCompatibility(){
        runBlocking {
            val fishes = repository.findByCompatibleFish(widthTank = 80, lengthTank = 30, fishIds = mutableListOf(7),aquariumRemainsSpace = 50).toList()
            assertEquals(4 , fishes.size)
            assertEquals("Barbus Ouro",fishes[0].name)
            assertEquals("Ramirezi",fishes[1].name)
            assertEquals("Colisa",fishes[2].name)
            assertEquals("Tricogaster",fishes[3].name)
        }
    }

    @Test
    fun listMyAquariumCompatibility(){
        runBlocking {
            val fishes = repository.findByCompatibleFish(widthTank = 80, lengthTank = 30, fishIds = mutableListOf(2,3,4,5,6),aquariumRemainsSpace = 50).toList()
            assertEquals(5 , fishes.size)
            assertEquals("Mato grosso",fishes[0].name)
            assertEquals("Tetra Negro",fishes[1].name)
            assertEquals("Barbus Ouro",fishes[2].name)
            assertEquals("Ramirezi",fishes[3].name)
            assertEquals("Colisa",fishes[4].name)
        }
    }

}