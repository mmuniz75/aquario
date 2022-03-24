package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.Flow
import muniz.aquarium.fishselector.domain.Fish
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface FishRepository : CoroutineCrudRepository<Fish, Int>  {

    suspend fun findByWidthTankLessThanEqualAndLengthTankLessThanEqual(widthTank : Int, lengthTank: Int) : Flow<Fish>


    @Query("""select f.*, p.*, d.*   
                    from fish f
                    INNER JOIN fish_ph fp ON f.id =  fp.fish_id
                    INNER JOIN fish_dh fd ON f.id =  fd.fish_id
                    INNER JOIN ph p ON fp.ph_id = p.id 
                    INNER JOIN dh d ON fd.dh_id = d.id  
                    where widthtank <= :widthTank  
                    and lengthtank <= :lengthTank  
                    and size * minnumber + initialspace <= :aquariumRemainsSpace
                    and f.id not in (:fishIds) 
                    and f.id in  
                                (select compatible_fish_id 
                                 from fish_compatility
                                 where fish_id in (:fishIds)
                                 group by compatible_fish_id
                                 having count(*)= :fishCount
                                 )
                   order by f.name              
                    """)
    suspend fun findByCompatibleFish(widthTank : Int, lengthTank: Int, fishIds : List<Int>, fishCount : Int, aquariumRemainsSpace : Int) : Flow<Fish>


    @Query("""select f.*, p.*, d.*   
                    from fish f
                    INNER JOIN fish_ph fp ON f.id =  fp.fish_id
                    INNER JOIN fish_dh fd ON f.id =  fd.fish_id
                    INNER JOIN ph p ON fp.ph_id = p.id 
                    INNER JOIN dh d ON fd.dh_id = d.id
                    where widthtank <= :widthTank  
                    and lengthtank <= :lengthTank  
                    and size * minnumber + initialspace <= :aquariumRemainsSpace
                    order by f.name
                    """)
    suspend fun findByCompatibleFishEmptyTank(widthTank : Int, lengthTank: Int, aquariumRemainsSpace : Int) : Flow<Fish>

    @Query("""select f.*, p.*, d.*   
                    from fish f
                    INNER JOIN fish_ph fp ON f.id =  fp.fish_id
                    INNER JOIN fish_dh fd ON f.id =  fd.fish_id
                    INNER JOIN ph p ON fp.ph_id = p.id 
                    INNER JOIN dh d ON fd.dh_id = d.id
                    where f.id in (:ids)
                    """)
    suspend fun findByIdIn(ids: List<Int>) : Flow<Fish>


}