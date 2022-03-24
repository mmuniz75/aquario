package muniz.aquarium.fishselector.application

import kotlinx.coroutines.flow.*
import muniz.aquarium.fishselector.domain.*
import muniz.aquarium.fishselector.dto.AddFishRequest
import muniz.aquarium.fishselector.dto.AquariumDTO
import muniz.aquarium.fishselector.dto.FishDTO
import muniz.aquarium.fishselector.dto.FishRequest
import muniz.aquarium.fishselector.exception.NotFoundException
import muniz.aquarium.fishselector.exception.PreConditionException
import muniz.aquarium.fishselector.infra.repository.FishAggregateRepository
import muniz.aquarium.fishselector.infra.repository.FishRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AquariumService {

    @Autowired
    private lateinit var repositoryAggregate: FishAggregateRepository

    @Autowired
    private lateinit var repository : FishRepository

    fun addNextQuestion(answer : HardScapeAnswer?,previousQuestions :  List<HardScapeAnswer>) : List<HardScapeAnswer?>{
        if(answer == null)
            return returnFirstQuestion()

        if(answer == null)
            throw PreConditionException("Resposta não pode ser vazia")

        val newPreviousList = mutableListOf<HardScapeAnswer?>()
        newPreviousList.addAll(previousQuestions)

        newPreviousList.add(answer)

        val nextQuestion =  if(answer.answer is Boolean)
                                 answer.hardScapeQuestion.getNext(answer.answer)
                            else
                                answer.hardScapeQuestion.getNext()

        if(nextQuestion!=null)
            newPreviousList.add(HardScapeAnswer(nextQuestion,null))
        else
            newPreviousList.add(null)

        return newPreviousList
    }

    fun returnFirstQuestion() : MutableList<HardScapeAnswer?>{
        return mutableListOf(HardScapeAnswer(HardScapeQuestion.getFirstQuestion(), null))
    }

    fun calculateAquariumAvailableSpace(tank : Tank, answers : List<HardScapeAnswer>) : Int{
        val hardScape = HardScape(answers, tank.width, tank.length)
        val aquarium =  Aquarium(tank, hardScape)
        return aquarium.fishCentimeterAvaliable;
    }

    suspend fun listFish(request : FishRequest) : Flow<FishDTO>{
        return if (request.currentFishIds.size>0)
                     repositoryAggregate.findByCompatibleFish(request.tankWidth, request.tankLength,request.currentFishIds, request.centimetersAvailable)
                        .map { FishDTO.fromDomain(it) }
               else
                    repositoryAggregate.findByCompatibleFishEmptyTank(request.tankWidth, request.tankLength, request.centimetersAvailable)
                        .map { FishDTO.fromDomain(it) }
    }

    suspend fun addFish(request: AddFishRequest): AquariumDTO {

        val fishs = if (request.currentFishIds.isEmpty()) listOf<Fish>() else repositoryAggregate.findByIdIn(request.currentFishIds).toList()

        val aquarium = Aquarium(request.centimetersAvailable, fishs.toMutableList())

        val fish = repositoryAggregate.findById(request.fishId)?:throw NotFoundException("Peixe não encontrado")

        aquarium.addFish(fish, request.fishCount)

        return AquariumDTO(aquarium.getTemperatureRange(),
                           aquarium.getPHRange(),
                           aquarium.getDHRange(),
                           aquarium.fishCentimeterAvaliable)


    }

}