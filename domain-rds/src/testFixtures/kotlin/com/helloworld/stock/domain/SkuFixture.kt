package com.helloworld.stock.domain

import com.helloworld.sku.domain.Sku
import java.math.BigDecimal

object SkuFixture {
    fun of(
        code: String = "code",
        name: String = "name",
        description: String = "description",
        supplyPrice: BigDecimal = BigDecimal.ZERO
    ) = Sku(
        code = code,
        name = name,
        description = description,
        supplyPrice = supplyPrice
    )
}
