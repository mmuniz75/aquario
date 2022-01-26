package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.Flow
import muniz.aquarium.fishselector.domain.Fish
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface FishRepository : CoroutineCrudRepository<Fish, Int>  {

    fun findByWidthTankLessThanEqualAndLengthTankLessThanEqual(widthTank : Int, lengthTank: Int) : Flow<Fish>
}