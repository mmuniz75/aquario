package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.Flow
import muniz.aquarium.fishselector.domain.Fish
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface FishRepository : CoroutineCrudRepository<Fish, Int>  {

    suspend fun findByWidthTankLessThanEqualAndLengthTankLessThanEqual(widthTank : Int, lengthTank: Int) : Flow<Fish>


    @Query("""select distinct * from fish  
                    where widthtank <= :widthTank  
                    and lengthtank <= :lengthTank  
                    and size * minnumber + initialspace <= :aquariumRemainsSpace
                    and id not in (:fishIds) 
                    and id in  
                                (select compatible_fish_id 
                                 from fish_compatility
                                 where fish_id in (:fishIds)
                                 group by compatible_fish_id
                                 having count(*)= :fishCount
                                 )
                   order by name              
                    """)
    suspend fun findByCompatibleFish(widthTank : Int, lengthTank: Int, fishIds : List<Int>, fishCount : Int, aquariumRemainsSpace : Int) : Flow<Fish>


    @Query("""select distinct * from fish  
                    where widthtank <= :widthTank  
                    and lengthtank <= :lengthTank  
                    and size * minnumber + initialspace <= :aquariumRemainsSpace
                    order by name
                    """)
    suspend fun findByCompatibleFishEmptyTank(widthTank : Int, lengthTank: Int, aquariumRemainsSpace : Int) : Flow<Fish>

    suspend fun findByIdIn(ids: List<Int>) : Flow<Fish>
}