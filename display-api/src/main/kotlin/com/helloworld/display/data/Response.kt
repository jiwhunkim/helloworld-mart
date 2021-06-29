package com.helloworld.display.data

import java.time.ZonedDateTime

class Response<T>(
    var timestamp: ZonedDateTime = ZonedDateTime.now(),
    var code: String = "200",
    var message: String = "OK",
    var result: T? = null
) {
    constructor(result: T) : this() {
        this.result = result
    }
}
