package com.helloworld.order.domain.service

import com.helloworld.order.domain.Cart
import com.helloworld.order.domain.CartLineItem
import com.helloworld.order.domain.CartRepository
import com.helloworld.order.domain.converter.CartProductConverter
import com.helloworld.order.domain.converter.CartProductOptionConverter
import com.helloworld.order.domain.converter.CartSellerProductConverter
import com.helloworld.product.domain.Product
import com.helloworld.product.domain.ProductOption
import com.helloworld.seller.domain.SellerProduct
import org.springframework.stereotype.Service

@Service
class DomainCartCommandService(
    private val cartProductConverter: CartProductConverter,
    private val cartProductOptionConverter: CartProductOptionConverter,
    private val cartSellerProductConverter: CartSellerProductConverter,
    private val cartRepository: CartRepository
) {
    fun save(cart: Cart): Cart {
        return cartRepository.save(cart)
    }

    fun createCartLineItem(
        product: Product,
        productOption: ProductOption,
        sellerProduct: SellerProduct,
        quantity: Int
    ): CartLineItem {
        return CartLineItem(
            id = null,
            cartProduct = cartProductConverter.convert(product),
            cartProductOption = cartProductOptionConverter.convert(productOption),
            cartSellerProduct = cartSellerProductConverter.convert(sellerProduct),
            quantity = quantity
        )
    }
}
