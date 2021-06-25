package com.helloworld.domain.cart

import com.helloworld.domain.product.ProductOption
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