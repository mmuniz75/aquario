package muniz.aquarium.fishselector.infra.resource

import muniz.aquarium.fishselector.application.AquariumService
import muniz.aquarium.fishselector.domain.HardScapeAnswer
import muniz.aquarium.fishselector.dto.HardScapeQuestionRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/aquarium")
class AquariumResource {

    @Autowired
    private lateinit var service: AquariumService

    @PostMapping("/hardscapeQuestion")
    fun getHardScapeQuestion(@RequestBody request : HardScapeQuestionRequest): MutableList<HardScapeAnswer?> {
        return service.addNextQuestion(request.answer, request.previousQuestions)
    }
}