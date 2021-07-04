package com.helloworld.order.domain.service

import com.helloworld.order.domain.Cart
import com.helloworld.order.domain.CartRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityNotFoundException

@Service
class DomainCartQueryService(val cartRepository: CartRepository) {
    fun queryByAccountId(accountId: Long): Optional<Cart> {
        return cartRepository.findByAccountId(accountId)
    }

    fun findByCartId(cartId: String): Cart {
        return cartRepository.findById(cartId).orElseThrow { EntityNotFoundException() }
    }
}
