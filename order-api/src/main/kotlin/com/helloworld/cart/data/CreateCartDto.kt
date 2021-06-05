package com.helloworld.cart.data

data class CreateCartDto(
    val productId: Long,
    val productOptionId: Long,
    val sellerProductId: Long,
    val quantity: Int
) {
}