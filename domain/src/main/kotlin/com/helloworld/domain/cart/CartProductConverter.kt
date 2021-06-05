package com.helloworld.domain.cart

import com.helloworld.domain.product.Product
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CartProductConverter : Converter<Product, CartProduct> {
    override fun convert(product: Product): CartProduct {
        return CartProduct(productId = product.id, productName = product.name)
    }
}