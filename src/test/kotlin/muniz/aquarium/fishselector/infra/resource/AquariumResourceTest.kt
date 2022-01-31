package muniz.aquarium.fishselector.infra.resource


import muniz.aquarium.fishselector.TestUtils
import muniz.aquarium.fishselector.application.AquariumService
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

    @Test
    fun getQuestions() {
        val URL = "/aquarium/hardscapeQuestion"

        var request = HardScapeAnswerRequest(null, null, null)
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