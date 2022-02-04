package muniz.aquarium.fishselector

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.io.Resources
import org.springframework.stereotype.Component
import org.springframework.test.web.reactive.server.EntityExchangeResult
import java.io.IOException


@Component
class TestUtils {

    fun readJson(resource: String): String {
        val mapper = ObjectMapper()
        return Resources.toString(Resources.getResource("jsons/$resource"), com.google.common.base.Charsets.UTF_8)
    }

    fun <T> readJson(resource: String, classObjt: Class<*>?): T {
        val mapper = ObjectMapper()
        val json: String = Resources.toString(Resources.getResource("jsons/$resource"), Charsets.UTF_8)
        return mapper.readValue(json, classObjt) as T
    }


    fun checkResponseWithJson(response: EntityExchangeResult<*>, json: String): Boolean {
        var expectedJson: String? = null
        expectedJson = try {
                json
                .replace("\r\n".toRegex(), "")
                .replace(" ".toRegex(), "")
        } catch (e: IOException) {
            e.printStackTrace()
            throw IllegalStateException(e.message)
        }
        val responseJson = String(response.responseBodyContent!!)
            .replace(" ".toRegex(), "")

        println("expceted json = $expectedJson")
        println("reponse json  = $responseJson")
        return expectedJson == responseJson
    }

    fun checkResponseWithResource(response: EntityExchangeResult<*>, jsonResourceFile: String): Boolean {
        return checkResponseWithJson(response,  this.readJson(jsonResourceFile))
    }

}