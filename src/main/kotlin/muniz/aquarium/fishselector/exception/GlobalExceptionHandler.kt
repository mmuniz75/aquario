package muniz.aquarium.fishselector.exception

import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*
import java.util.Map


@ControllerAdvice
class GlobalExceptionHandler {

    private val CONFLIT_MSG = Map.of("video_number_unique", "Video already exists"

    )

    var logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(Exception::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected fun processException(ex: Exception?) {
        logE(ex)
    }

    @ExceptionHandler(APIException::class)
    protected fun processAPIException(ex: APIException): ResponseEntity<ErrorMessage> {
        val status: ResponseStatus = ex.javaClass.getDeclaredAnnotation(ResponseStatus::class.java)
        logE(ex)
        return ResponseEntity<ErrorMessage>(ex.error,
                if (Objects.nonNull(status)) status.code else HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(ex: DataIntegrityViolationException): ResponseEntity<ErrorMessage?>? {
        logE(ex)
        var exMessage = ex.cause!!.message

        exMessage = exMessage?.replace("\"","")
        exMessage = exMessage?.substring(exMessage!!.indexOf("constraint ") + 11)

        val message = if (CONFLIT_MSG.containsKey(exMessage)) CONFLIT_MSG[exMessage] else exMessage!!
        val errorMessage = ErrorMessage(message!!)
        return ResponseEntity(errorMessage, HttpStatus.CONFLICT)
    }

    private fun logE(e: Exception?) {
        logger.info("e={},m={}", e?.javaClass?.simpleName, e?.message)
    }
}