package com.helloworld.display.data

data class DisplayPageDto(
    val id: Long,
    val mallId: Long,
    val name: String,
    val code: String
) {
    constructor() : this(id = 0L, mallId = 0L, name = "name", code = "main")
}
