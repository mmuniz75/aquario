package muniz.aquarium.fishselector.infra.repository

import muniz.aquarium.fishselector.domain.DH
import muniz.aquarium.fishselector.domain.PH
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface DHRepository : CoroutineCrudRepository<DH, Int>  {
}