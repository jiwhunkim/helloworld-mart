package com.helloworld.order.domain

import java.math.BigDecimal

class CartSellerProduct(
    val sellerProductId: Long,
    val sellerProductName: String,
    val salesAmount: BigDecimal,
    val discountAmount: BigDecimal,
    val amount: BigDecimal
)
