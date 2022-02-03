package muniz.aquarium.fishselector.infra.resource


import muniz.aquarium.fishselector.TestUtils
import muniz.aquarium.fishselector.application.AquariumService
import muniz.aquarium.fishselector.domain.Tank
import muniz.aquarium.fishselector.dto.AquariumSpaceRequest
import muniz.aquarium.fishselector.dto.FishRequest
import muniz.aquarium.fishselector.dto.HardScapeAnswerDTO
import muniz.aquarium.fishselector.dto.HardScapeAnswerRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono


@SpringBootTest
@AutoConfigureWebTestClient
class AquariumResourceTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var service: AquariumService

    @Autowired
    private lateinit var testUtils: TestUtils

    private val URL = "/aquarium"

    @Test
    fun checkAquariumSpace() {
        val request = AquariumSpaceRequest(Tank(80,30,45),
                                           mutableListOf(HardScapeAnswerDTO("SUBSTRACT_WEIGHT",10),
                                                         HardScapeAnswerDTO("ROCK_WEIGHT",6),
                                                         HardScapeAnswerDTO("WOOD_WEIGHT",1)))
        executePost("avaliableSpace",request,"space.json");
    }

    @Test
    fun listNeonCompatibility() {
        val request = FishRequest(80,30,50, listOf(1))
        executePost("fish",request,"fishs/neon_response.json");
    }

    @Test
    fun listNeonMatoGrossoTetraCompatibility() {
        val request = FishRequest(80,30,50, listOf(1,2,3))
        executePost("fish",request,"fishs/neon_response.json");
    }

    @Test
    fun listMatoGrossoTetraCompatibility() {
        val request = FishRequest(80,30,50, listOf(2,3))
        executePost("fish",request,"fishs/mato_tetra_response.json");
    }

    @Test
    fun listTricogasterCompatibility() {
        val request = FishRequest(80,30,50, listOf(7))
        executePost("fish",request,"fishs/tricogaster_response.json");
    }

    @Test
    fun listCompatibility() {
        val request = FishRequest(80,30,50, listOf(2,3,4,5,6))
        executePost("fish",request,"fishs/response.json");
    }

    @Test
    fun checkQuestion1() {
        var request = HardScapeAnswerRequest(null, null, null)
        executePost("hardscapeQuestion", request, "questions/question_1_response.json")
    }

    @Test
    fun checkQuestion2() {
        var request = HardScapeAnswerRequest("SUBSTRACT_KNOLEDGEMENT", true, null)
        executePost("hardscapeQuestion", request, "questions/question_2_response.json")
    }

    @Test
    fun checkQuestion3() {
        var request = HardScapeAnswerRequest("SUBSTRACT_WEIGHT", 10, mutableListOf(HardScapeAnswerDTO("SUBSTRACT_KNOLEDGEMENT", true)))
        executePost("hardscapeQuestion", request, "questions/question_3_response.json")
    }

    @Test
    fun checkQuestion4() {
        var request = HardScapeAnswerRequest("ROCK_EXISTENCE", true,
                                                      mutableListOf(HardScapeAnswerDTO("SUBSTRACT_KNOLEDGEMENT", true),
                                                                    HardScapeAnswerDTO("SUBSTRACT_WEIGHT", 10,
                                                      )))
        executePost("hardscapeQuestion",request, "questions/question_4_response.json")
    }

    @Test
    fun checkQuestion5() {
        var request = HardScapeAnswerRequest("ROCK_KNOLEDGEMENT", true,
            mutableListOf(HardScapeAnswerDTO("SUBSTRACT_KNOLEDGEMENT", true),
                          HardScapeAnswerDTO("SUBSTRACT_WEIGHT", 10),
                          HardScapeAnswerDTO("ROCK_EXISTENCE", true)
            ))
        executePost("hardscapeQuestion",request, "questions/question_5_response.json")
    }

    @Test
    fun checkQuestion6() {
        var request = HardScapeAnswerRequest("ROCK_WEIGHT", 5,
            mutableListOf(HardScapeAnswerDTO("SUBSTRACT_KNOLEDGEMENT", true),
                          HardScapeAnswerDTO("SUBSTRACT_WEIGHT", 10),
                          HardScapeAnswerDTO("ROCK_EXISTENCE", true),
                          HardScapeAnswerDTO("ROCK_KNOLEDGEMENT", true)
            ))
        executePost("hardscapeQuestion",request, "questions/question_6_response.json")
    }

    @Test
    fun checkQuestion7() {
        var request = HardScapeAnswerRequest("WOOD_EXISTENCE", true,
            mutableListOf(HardScapeAnswerDTO("SUBSTRACT_KNOLEDGEMENT", true),
                HardScapeAnswerDTO("SUBSTRACT_WEIGHT", 10),
                HardScapeAnswerDTO("ROCK_EXISTENCE", true),
                HardScapeAnswerDTO("ROCK_KNOLEDGEMENT", true),
                HardScapeAnswerDTO("ROCK_WEIGHT", 5)
            ))
        executePost("hardscapeQuestion",request, "questions/question_7_response.json")
    }

    @Test
    fun checkQuestion8() {
        var request = HardScapeAnswerRequest("WOOD_KNOLEDGEMENT", true,
            mutableListOf(HardScapeAnswerDTO("SUBSTRACT_KNOLEDGEMENT", true),
                HardScapeAnswerDTO("SUBSTRACT_WEIGHT", 10),
                HardScapeAnswerDTO("ROCK_EXISTENCE", true),
                HardScapeAnswerDTO("ROCK_KNOLEDGEMENT", true),
                HardScapeAnswerDTO("ROCK_WEIGHT", 5),
                HardScapeAnswerDTO("WOOD_EXISTENCE", true)
            ))
        executePost("hardscapeQuestion",request, "questions/question_8_response.json")
    }

    @Test
    fun checkQuestion9() {
        var request = HardScapeAnswerRequest("WOOD_WEIGHT", 2,
            mutableListOf(HardScapeAnswerDTO("SUBSTRACT_KNOLEDGEMENT", true),
                HardScapeAnswerDTO("SUBSTRACT_WEIGHT", 10),
                HardScapeAnswerDTO("ROCK_EXISTENCE", true),
                HardScapeAnswerDTO("ROCK_KNOLEDGEMENT", true),
                HardScapeAnswerDTO("ROCK_WEIGHT", 5),
                HardScapeAnswerDTO("WOOD_EXISTENCE", true),
                HardScapeAnswerDTO("WOOD_KNOLEDGEMENT", true)
            ))
        executePost("hardscapeQuestion", request, "questions/question_9_response.json")
    }

    private fun executePost(endPoint : String, request : Any, response : String) {
        webTestClient.post()
            .uri("$URL/$endPoint")
            .body(Mono.just(request), request::class.java)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .consumeWith {
                assertTrue(testUtils.checkResponse(it,response)) }
    }

}