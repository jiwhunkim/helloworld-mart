package com.helloworld.display.domain.dto

data class DisplayProductDto(
    val productId: Long,
    val display: Boolean = true,
    val sale: Boolean = true,
    val soldOut: Boolean = false,
) {

    constructor() : this(productId = 1L)
}
