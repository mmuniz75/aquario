package muniz.aquarium.fishselector.infra.repository

import kotlinx.coroutines.flow.Flow
import muniz.aquarium.fishselector.domain.DH
import muniz.aquarium.fishselector.domain.PH
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface DHRepository : CoroutineCrudRepository<DH, Int>  {

    @Query("select d.* from dh d, fish_dh f where d.id = f.dh_id and f.fish_id = :fishId")
    suspend fun findDHByFishId(fishId : Int) : Flow<DH>;
}