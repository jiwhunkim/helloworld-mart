package com.helloworld.domain.cart

import java.math.BigDecimal

class CartProductOption(
    productOptionId: Long,
    productOptionName: String,
    salesAmount: BigDecimal,
    discountAmount: BigDecimal,
    amount: BigDecimal
) {
    var productOptionId: Long = productOptionId
    var productOptionName: String = productOptionName
    var salesAmount: BigDecimal = salesAmount
    var discountAmount: BigDecimal = discountAmount
    var amount: BigDecimal = salesAmount.minus(discountAmount)
}
