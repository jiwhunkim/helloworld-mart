package com.helloworld.display.data.dto.response

import com.helloworld.display.domain.dto.product.DisplayProductDto
import com.helloworld.product.domain.dto.ProductDto
import com.helloworld.product.domain.dto.ProductOptionDto
import java.math.BigDecimal

data class DisplayProductResponseDto(
    val id: Long,
    val code: String,
    val name: String,
    val description: String,
    val mallId: Long,
    val display: Boolean,
    val sale: Boolean,
    val soldOut: Boolean,
    val salesAmount: BigDecimal,
    val discountAmount: BigDecimal,
    val amount: BigDecimal

) {
    constructor(displayProduct: DisplayProductDto, product: ProductDto, productOption: ProductOptionDto) : this(
        id = product.id,
        code = product.code,
        name = product.name,
        description = product.description,
        mallId = product.mallId,
        display = displayProduct.display,
        sale = displayProduct.sale,
        soldOut = displayProduct.soldOut,
        salesAmount = productOption.salesAmount,
        discountAmount = productOption.discountAmount,
        amount = productOption.amount
    )
}
