package com.helloworld.display.domain.dto

data class DisplayProductDto(
    val code: String,
    val name: String,
    val description: String
) {

    constructor() : this(code = "code", name = "name", description = "description")
}
