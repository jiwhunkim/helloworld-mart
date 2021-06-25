package com.helloworld.domain.cart

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