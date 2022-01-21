package muniz.aquarium.fishselector.infra.repository

import muniz.aquarium.fishselector.domain.Fish
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface FishRepository : CoroutineCrudRepository<Fish, Int>  {
}