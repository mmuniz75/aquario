package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class FishAggregateRepository {

    @Autowired
    private lateinit var repository: FishRepository

    @Autowired
    private lateinit var phRepository: PHRepository

    @Autowired
    private lateinit var dhRepository: DHRepository

    suspend fun listFish(){

        val fishes = repository.findAll()

    }

}