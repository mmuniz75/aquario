package muniz.aquarium.fishselector.infra.resource


import muniz.aquarium.fishselector.TestUtils
import muniz.aquarium.fishselector.application.AquariumService
import muniz.aquarium.fishselector.dto.HardScapeAnswerDTO
import muniz.aquarium.fishselector.dto.HardScapeAnswerRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
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

    private val URL = "/aquarium/hardscapeQuestion"

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

    private fun executePost(request : HardScapeAnswerRequest, response : String) {
        webTestClient.post()
            .uri(URL)
            .body(Mono.just(request), HardScapeAnswerRequest::class.java)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .consumeWith {
                testUtils.checkResponse(it,"questions/question_1_response.json") }
    }

}