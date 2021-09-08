package com.helloworld.product.domain.dto

import java.math.BigDecimal

data class ProductOptionDto(
    val id: Long,
    val code: String,
    val name: String,
    val description: String,
    val productId: Long,
    val salesAmount: BigDecimal,
    val discountAmount: BigDecimal,
    val amount: BigDecimal,
    val representative: Boolean
)
