package com.helloworld.domain.cart.service

import com.helloworld.domain.cart.Cart
import com.helloworld.domain.cart.CartRepository
import org.springframework.stereotype.Service

@Service
class CartCommandService(private val cartRepository: CartRepository) {
    fun save(cart: Cart): Cart {
        return cartRepository.save(cart)
    }
}
