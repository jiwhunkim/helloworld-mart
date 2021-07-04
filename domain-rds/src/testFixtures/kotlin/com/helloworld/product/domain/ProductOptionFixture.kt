package com.helloworld.product.domain

import com.helloworld.seller.domain.SellerProduct
import java.math.BigDecimal

object ProductOptionFixture {
    fun of(
        code: String = "code",
        name: String = "name",
        description: String = "description",
        productId: Long = 1L,
        sellerProduct: SellerProduct,
        salesAmount: BigDecimal = BigDecimal(0),
        discountAmount: BigDecimal = BigDecimal(0),
        amount: BigDecimal = BigDecimal(0)
    ) = ProductOption(
        code = code,
        name = name,
        description = description,
        productId = productId,
        sellerProduct = sellerProduct,
        salesAmount = salesAmount,
        discountAmount = discountAmount,
        amount = amount
    )
}
