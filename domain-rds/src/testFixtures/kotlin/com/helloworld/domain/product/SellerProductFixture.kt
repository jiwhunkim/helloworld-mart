package com.helloworld.domain.product

import java.math.BigDecimal

object SellerProductFixture {
    fun of(
        code: String = "code",
        name: String = "name",
        description: String = "description",
        seller: Seller,
        sku: Sku,
        salesAmount: BigDecimal = BigDecimal.ZERO
    ) = SellerProduct(
        code = code,
        name = name,
        description = description,
        seller = seller,
        sku = sku,
        salesAmount = salesAmount
    )
}
