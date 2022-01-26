package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.Flow
import muniz.aquarium.fishselector.domain.Fish
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface FishRepository : CoroutineCrudRepository<Fish, Int>  {

    fun findByWidthTankLessThanEqualAndLengthTankLessThanEqual(widthTank : Int, lengthTank: Int) : Flow<Fish>


    @Query("""select f.* from fish f, fish_compatility fc  
                    where f.id = fc.fish_id  
                    and fc.compatible_fish_id in (:fishIds)  
                    and f.widthtank <= :widthTank  
                    and f.lengthtank <= :lengthTank  
                    and f.size * f.minnumber <= :aquariumRemainsSpace""")
    fun findByCompatibleFish(widthTank : Int, lengthTank: Int, fishIds : List<Int>, aquariumRemainsSpace : Int) : Flow<Fish>
}