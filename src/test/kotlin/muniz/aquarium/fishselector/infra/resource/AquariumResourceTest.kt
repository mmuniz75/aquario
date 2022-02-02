package muniz.aquarium.fishselector.infra.resource


import muniz.aquarium.fishselector.TestUtils
import muniz.aquarium.fishselector.application.AquariumService
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
        executePost("$URL/avaliableSpace","space_request.json","space.json");
    }

    @Test
    fun listNeonCompatibility() {
        executePost("$URL/listFish","fishs/neon_request.json","fishs/neon_response.json");
    }

    private fun executePost(url : String, request : String, response: String) {
        webTestClient.post()
            .uri(url)
            .header("Content-Type","application/json")
            .bodyValue(testUtils.readJson(request))
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .consumeWith {
                assertTrue(testUtils.checkResponse(it,response)) }
    }

    @Test
    fun checkQuestion1() {
        var request = HardScapeAnswerRequest(null, null, null)
        executePost(request, "questions/question_1_response.json")
    }

    @Test
    fun checkQuestion2() {
        var request = HardScapeAnswerRequest("SUBSTRACT_KNOLEDGEMENT", true, null)
        executePost(request, "questions/question_2_response.json")
    }

    @Test
    fun checkQuestion3() {
        var request = HardScapeAnswerRequest("SUBSTRACT_WEIGHT", 10, mutableListOf(HardScapeAnswerDTO("SUBSTRACT_KNOLEDGEMENT", true)))
        executePost(request, "questions/question_3_response.json")
    }

    @Test
    fun checkQuestion4() {
        var request = HardScapeAnswerRequest("ROCK_EXISTENCE", true,
                                                      mutableListOf(HardScapeAnswerDTO("SUBSTRACT_KNOLEDGEMENT", true),
                                                                    HardScapeAnswerDTO("SUBSTRACT_WEIGHT", 10,
                                                      )))
        executePost(request, "questions/question_4_response.json")
    }

    @Test
    fun checkQuestion5() {
        var request = HardScapeAnswerRequest("ROCK_KNOLEDGEMENT", true,
            mutableListOf(HardScapeAnswerDTO("SUBSTRACT_KNOLEDGEMENT", true),
                          HardScapeAnswerDTO("SUBSTRACT_WEIGHT", 10),
                          HardScapeAnswerDTO("ROCK_EXISTENCE", true)
            ))
        executePost(request, "questions/question_5_response.json")
    }

    @Test
    fun checkQuestion6() {
        var request = HardScapeAnswerRequest("ROCK_WEIGHT", 5,
            mutableListOf(HardScapeAnswerDTO("SUBSTRACT_KNOLEDGEMENT", true),
                          HardScapeAnswerDTO("SUBSTRACT_WEIGHT", 10),
                          HardScapeAnswerDTO("ROCK_EXISTENCE", true),
                          HardScapeAnswerDTO("ROCK_KNOLEDGEMENT", true)
            ))
        executePost(request, "questions/question_6_response.json")
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
        executePost(request, "questions/question_7_response.json")
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
        executePost(request, "questions/question_8_response.json")
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
        executePost(request, "questions/question_9_response.json")
    }

    private fun executePost(request : HardScapeAnswerRequest, response : String) {
        webTestClient.post()
            .uri("$URL/hardscapeQuestion")
            .body(Mono.just(request), HardScapeAnswerRequest::class.java)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .consumeWith {
                assertTrue(testUtils.checkResponse(it,response)) }
    }

}