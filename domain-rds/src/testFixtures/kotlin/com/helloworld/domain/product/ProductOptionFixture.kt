package com.helloworld.domain.product

import java.math.BigDecimal

object ProductOptionFixture {
    fun of(
        code: String = "code",
        name: String = "name",
        description: String = "description",
        sellerProduct: SellerProduct,
        salesAmount: BigDecimal = BigDecimal(0),
        discountAmount: BigDecimal = BigDecimal(0),
        amount: BigDecimal = BigDecimal(0)
    ) = ProductOption(
        code = code,
        name = name,
        description = description,
        sellerProduct = sellerProduct,
        salesAmount = salesAmount,
        discountAmount = discountAmount,
        amount = amount
    )
}
