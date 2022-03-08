package muniz.aquarium.fishselector.exception

abstract class APIException : RuntimeException {
    var error: ErrorMessage

    constructor(cause: Throwable) : super(cause) {
        this.error = ErrorMessage(cause.message!!)
    }

    constructor(error: ErrorMessage) {
        this.error = error
    }

    constructor(error: String?) {
        this.error = ErrorMessage(error!!)
    }

}
