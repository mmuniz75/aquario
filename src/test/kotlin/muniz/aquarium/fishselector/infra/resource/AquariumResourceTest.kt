package muniz.aquarium.fishselector.infra.resource


import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import muniz.aquarium.fishselector.TestUtils
import muniz.aquarium.fishselector.application.AquariumService
import muniz.aquarium.fishselector.domain.Tank
import muniz.aquarium.fishselector.dto.*
import muniz.aquarium.fishselector.infra.repository.FishRepository
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

    @Autowired
    private lateinit var objectMapper : ObjectMapper

    @Autowired
    private lateinit var repository: FishRepository

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
        executePost("fish",request,"fishs/empty.json");
    }

    @Test
    fun listMatoGrossoTetraCompatibility() {
        val request = FishRequest(80,30,50, listOf(2,3))
        executePost("fish",request,"fishs/mato_tetra_response.json");
    }

    @Test
    fun listTricogasterCompatibility() {
        val request = FishRequest(80,30,50, listOf(7))
        defeitoNoH2FazendoAcessoNoBancoCorrige()
        executePost("fish",request,"fishs/tricogaster_response.json");
    }

    fun defeitoNoH2FazendoAcessoNoBancoCorrige(){
        runBlocking {
            repository.findByCompatibleFish(100,50, listOf(7),1,100)
                .collect {   }
        }
    }

    @Test
    fun listCompatibility() {
        val request = FishRequest(80,30,50, listOf(2,3,4,5,6))
        executePost("fish",request,"fishs/empty.json");
    }

    @Test
    fun listKilliFishTankLenghtSize() {
        val request = FishRequest(80,20,32, listOf())
        executePost("fish",request,"fishs/killi_fish_response.json");
    }

    @Test
    fun listKilliFishTankLenghtSizeWithFish() {
        val request = FishRequest(80,20,50, listOf(3))
        executePost("fish",request,"fishs/killi_fish_response.json");
    }

    @Test
    fun listAllCompatibility() {
        val request = FishRequest(80,40,120, listOf())
        executePost("fish",request,"fishs/all.json");
    }

    @Test
    fun listNotDisplayKinguioMissingRoom() {
        val request = FishRequest(80,40,50, listOf())
        executePost("fish",request,"fishs/missing_kinguio.json");
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

    @Test
    fun checkFishCentimerOneShoal(){
        executePut("fish",
                    AddFishRequest(1,10,81, listOf(1)),
                    AquariumDTO("26Cº - 28Cº","6.2 - 6.8","muito mole até mole",51)
        )
    }

    @Test
    fun checkFishCentimerSEcondShoal(){
        executePut("fish",
            AddFishRequest(3,7,51, listOf(2)),
            AquariumDTO("26Cº - 28Cº","6.2 - 6.8","media",16)
        )
    }

    @Test
    fun addKinguio(){
        executePut("fish",
            AddFishRequest(21,1,120, listOf()),
            AquariumDTO("10Cº - 28Cº","7.0","mole até dura",0)
        )
    }

    @Test
    fun addTwoKinguio(){
        executePut("fish",
            AddFishRequest(21,2,150, listOf()),
            AquariumDTO("10Cº - 28Cº","7.0","mole até dura",0)
        )
    }

    @Test
    fun checkFishCentimerKinguioMissingSpace(){
        val request = AddFishRequest(21, 2, 120, listOf())
        perform400Put(request)
    }

    @Test
    fun checkFishCentimerOneKinguioSmalTankShouldFail(){
        val request = AddFishRequest(21, 1, 119, listOf())
        perform400Put(request)
    }

    private fun perform400Put(request : AddFishRequest){
        webTestClient.put()
            .uri("$URL/fish")
            .body(Mono.just(request), request::class.java)
            .exchange()
            .expectStatus().is4xxClientError
            .expectBody()
            .jsonPath("$.message").isEqualTo("Não é possivel adicionar esses peixes pois o aquario não suporta essa quandidade")
    }

    @Autowired
    private lateinit var mapper : ObjectMapper

    private fun executePost(endPoint : String, request : Any, response : String) {
        println("request = ${mapper.writeValueAsString(request)}")
        webTestClient.post()
            .uri("$URL/$endPoint")
            .body(Mono.just(request), request::class.java)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .consumeWith {
                assertTrue(testUtils.checkResponseWithResource(it,response)) }
    }

    private fun executePut(endPoint : String, request : AddFishRequest, dto : AquariumDTO) {
        webTestClient.put()
            .uri("$URL/$endPoint")
            .body(Mono.just(request), request::class.java)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .consumeWith {
                assertTrue(testUtils.checkResponseWithJson(it,objectMapper.writeValueAsString(dto))) }

    }

}