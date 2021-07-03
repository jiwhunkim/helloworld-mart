package com.helloworld.display.data.dto.response

import com.helloworld.display.domain.dto.DisplayProductDto
import com.helloworld.seller.domain.dto.SellerDto
import java.math.BigDecimal

data class DisplayProductResponseDto(
    val id: Long,
    val code: String,
    val name: String,
    val description: String,
    val display: Boolean,
    val sale: Boolean,
    val soldOut: Boolean,
    val salesAmount: BigDecimal,
    val discountAmount: BigDecimal,
    val amount: BigDecimal,
    val seller: SellerDto

) {
    constructor(displayProduct: DisplayProductDto, seller: SellerDto) : this(
        id = 0L,
        code = displayProduct.code,
        name = displayProduct.name,
        description = displayProduct.description,
        display = true,
        sale = true,
        soldOut = true,
        salesAmount = BigDecimal.ZERO,
        discountAmount = BigDecimal.ZERO,
        amount = BigDecimal.ZERO,
        seller = seller
    )
}
