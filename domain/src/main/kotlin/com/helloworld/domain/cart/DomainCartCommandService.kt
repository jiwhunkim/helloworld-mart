package com.helloworld.domain.cart

import org.springframework.stereotype.Service

@Service
class DomainCartCommandService(
    private val cartRepository: CartRepository
) {
    fun placeCart(accountId: Long, cartLineItem: CartLineItem): Cart {
        val cart = Cart(accountId = accountId, listOf(cartLineItem))
        return cartRepository.save(cart)
    }
}