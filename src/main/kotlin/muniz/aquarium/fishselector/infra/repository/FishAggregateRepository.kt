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
        return addPHDH(repository.findAll())
    }

    suspend fun listFishByTank(widthTank : Int, lengthTank: Int) : Flow<Fish>{
        return addPHDH(repository.findByWidthTankLessThanEqualAndLengthTankLessThanEqual(widthTank, lengthTank))
    }

    suspend fun findByCompatibleFish(widthTank : Int, lengthTank: Int, fishIds : List<Int>, aquariumRemainsSpace : Int) : Flow<Fish> {
        return addPHDH(repository.findByCompatibleFish(widthTank, lengthTank,fishIds, aquariumRemainsSpace))
    }

    private suspend fun addPHDH(fishes : Flow<Fish>): Flow<Fish> {
        return fishes
                .onEach { it.ph =  phRepository.findPHByFishId(it.id).toList()}
                .onEach { it.dh =  dhRepository.findDHByFishId(it.id).toList()}
    }

}