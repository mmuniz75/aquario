package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import muniz.aquarium.fishselector.domain.Fish
import muniz.aquarium.fishselector.exception.NotFoundException
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
        return addPHDH(repository.findByCompatibleFish(widthTank, lengthTank,fishIds, fishIds.size, aquariumRemainsSpace))
    }

    suspend fun findByCompatibleFishEmptyTank(widthTank : Int, lengthTank: Int, aquariumRemainsSpace : Int) : Flow<Fish> {
        return addPHDH(repository.findByCompatibleFishEmptyTank(widthTank, lengthTank, aquariumRemainsSpace))
    }

    suspend fun findByIdIn(ids: List<Int>) : Flow<Fish> {
        return addPHDH(repository.findByIdIn(ids))
    }

    private suspend fun addPHDH(fishesDuplicates : Flow<Fish>): Flow<Fish> {
        val fishes = mutableListOf<Fish>()
        var id = 0

        return flow {

            fishesDuplicates.toList().forEach {
                if (it.id == id) {
                    val fish = fishes.last()

                    if (!fish.ph.contains(it.ph[0]))
                        fish.ph.add(it.ph[0].copy())

                    if (!fish.dh.contains(it.dh[0]))
                        fish.dh.add(it.dh[0].copy())
                } else {
                    if(fishes.size > 0)
                        emit(fishes.last())
                    fishes.add(it.copy())
                    id = it.id
                }
            }

            if(!fishes.isEmpty())
                emit(fishes.last())
        }

    }

    suspend fun findById(id: Int): Fish? {
        val fish = repository.findById(id)?: throw NotFoundException("Peixe não encontrado")
        fish.ph = phRepository.findPHByFishId(id).toList().toMutableList()
        fish.dh = dhRepository.findDHByFishId(id).toList().toMutableList()

        return fish
    }



}