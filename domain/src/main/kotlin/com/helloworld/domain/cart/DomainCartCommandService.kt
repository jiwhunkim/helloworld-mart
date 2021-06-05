package com.helloworld.domain.cart

import com.helloworld.domain.product.SellerProductRepository
import org.springframework.stereotype.Service

@Service
class DomainCartCommandService(
    private val cartRepository: CartRepository,
    private val sellerProductRepository: SellerProductRepository
) {
    fun placeCart(accountId: Long, cartLineItem: CartLineItem): Cart {
        val cart = Cart(accountId = accountId, listOf(cartLineItem))
        return cartRepository.save(cart)
    }
}