package com.helloworld.display.domain.dto.product

data class DisplayProductDto(
    val id: Long,
    val productId: Long,
    val display: Boolean,
    val sale: Boolean,
    val soldOut: Boolean
)
