package com.helloworld.order.data.cart

data class CreateCartDto(
    val productId: Long,
    val productOptionId: Long,
    val sellerProductId: Long,
    val quantity: Int
)
