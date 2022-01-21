package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.*
import muniz.aquarium.fishselector.domain.Fish
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

    suspend fun listFish() : Flow<Fish>{
        return repository.findAll()
               .onEach { it.ph =  phRepository.findPHByFishId(it.id).toList()}
    }

}