package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.Flow
import muniz.aquarium.fishselector.domain.PH
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface PHRepository : CoroutineCrudRepository<PH, Int>  {

    @Query("select p.* from ph p, fish_ph fph where p.id = fph.fish_id and p.id = %fishId")
    suspend fun findPHByFishId(fishId : Int) : Flow<PH>;
}