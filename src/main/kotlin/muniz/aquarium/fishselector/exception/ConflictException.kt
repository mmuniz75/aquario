package muniz.aquarium.fishselector.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.CONFLICT)
class ConflictException : APIException {
    constructor(cause: Throwable?) : super(cause!!) {}
    constructor(error: ErrorMessage?) : super(error!!) {}
    constructor(error: String?) : super(error) {}
}
