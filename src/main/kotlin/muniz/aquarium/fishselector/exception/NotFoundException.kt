package muniz.aquarium.fishselector.exception

import muniz.aquarium.fishselector.exception.APIException
import muniz.aquarium.fishselector.exception.ErrorMessage
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class NotFoundException : APIException {
    constructor(cause: Throwable?) : super(cause!!) {}
    constructor(error: ErrorMessage?) : super(error!!) {}
    constructor(error: String?) : super(error) {}
}
