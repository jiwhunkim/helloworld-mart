package com.helloworld.data.cart

data class CartDto(
    val id: String,
    val accountId: Long,
    val cartLineItems: List<CartLineItemDto>
) {
}