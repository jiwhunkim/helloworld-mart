package com.helloworld.order.domain.converter

import com.helloworld.order.domain.CartProductOption
import com.helloworld.product.domain.ProductOption
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CartProductOptionConverter : Converter<ProductOption, CartProductOption> {
    override fun convert(productOption: ProductOption): CartProductOption {
        return CartProductOption(
            productOptionId = productOption.id,
            productOptionName = productOption.name,
            salesAmount = productOption.salesAmount,
            discountAmount = productOption.discountAmount,
            amount = productOption.amount
        )
    }
}
