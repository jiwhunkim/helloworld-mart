package com.helloworld.order.domain.dto

data class CartDto(
    val id: String,
    val accountId: Long,
    val cartLineItems: List<CartLineItemDto>
)
