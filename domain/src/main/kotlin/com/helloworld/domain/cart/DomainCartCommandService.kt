package com.helloworld.domain.cart

import com.helloworld.domain.product.Product
import com.helloworld.domain.product.ProductOption
import com.helloworld.domain.product.SellerProduct
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