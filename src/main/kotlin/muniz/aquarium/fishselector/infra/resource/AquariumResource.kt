package muniz.aquarium.fishselector.infra.resource

import muniz.aquarium.fishselector.application.AquariumService
import muniz.aquarium.fishselector.domain.Aquarium
import muniz.aquarium.fishselector.domain.HardScapeAnswer
import muniz.aquarium.fishselector.domain.HardScapeQuestion
import muniz.aquarium.fishselector.domain.Tank
import muniz.aquarium.fishselector.dto.AquariumSpaceRequest
import muniz.aquarium.fishselector.dto.AquariumSpaceResponse
import muniz.aquarium.fishselector.dto.HardScapeAnswerDTO
import muniz.aquarium.fishselector.dto.HardScapeAnswerRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/aquarium")
class AquariumResource {

    @Autowired
    private lateinit var service: AquariumService

    @PostMapping("/hardscapeQuestion")
    fun getHardScapeQuestion(@RequestBody request : HardScapeAnswerRequest): List<HardScapeAnswer?> {
        val question = HardScapeQuestion.fromId(request.question)
        val answer = if (question!=null) HardScapeAnswer(question, request.answer) else null
        return service.addNextQuestion(answer, convertPreviousQuestions(request.previousAnswers))
    }

    @PostMapping("/avaliableSpace")
    fun getAvaliableSpace(@RequestBody request : AquariumSpaceRequest): AquariumSpaceResponse {
        return AquariumSpaceResponse(service.calculateAquariumAvaliableSpace(request.tank,convertPreviousQuestions(request.answers)))
    }

    private fun convertPreviousQuestions(previousQuestions : List<HardScapeAnswerDTO?>?) : List<HardScapeAnswer>{
        if(previousQuestions == null)
           return listOf()

        return previousQuestions.mapNotNull { val question = HardScapeQuestion.fromId(it?.questionId)
                                              if (question !=null) HardScapeAnswer(question, it?.answer) else null
                                            }
            ?.toMutableList()
    }
}