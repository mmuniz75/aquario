package muniz.aquarium.fishselector.infra.resource

import muniz.aquarium.fishselector.application.AquariumService
import muniz.aquarium.fishselector.domain.HardScapeAnswer
import muniz.aquarium.fishselector.domain.HardScapeQuestion
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
    fun getHardScapeQuestion(@RequestBody request : HardScapeAnswerRequest): MutableList<HardScapeAnswer?> {
        val question = HardScapeQuestion.fromId(request.question)
        val answer = if (question!=null) HardScapeAnswer(question, request.answer) else null
        return service.addNextQuestion(answer, convertPreviousQuestions(request.previousAnswers))
    }

    private fun convertPreviousQuestions(previousQuestions : MutableList<HardScapeAnswerDTO?>?) : MutableList<HardScapeAnswer?>{
        if(previousQuestions == null)
           return mutableListOf()

        return previousQuestions.mapNotNull { val question = HardScapeQuestion.fromId(it?.id)
                                              if (question !=null) HardScapeAnswer(question, it?.answer) else null
                                            }
            ?.toMutableList()
    }
}