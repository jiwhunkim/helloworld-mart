package com.helloworld.order.domain.service

import com.helloworld.order.domain.Cart
import com.helloworld.order.domain.CartRepository
import org.springframework.stereotype.Service

@Service
class CartCommandService(private val cartRepository: CartRepository) {
    fun save(cart: Cart): Cart {
        return cartRepository.save(cart)
    }
}
