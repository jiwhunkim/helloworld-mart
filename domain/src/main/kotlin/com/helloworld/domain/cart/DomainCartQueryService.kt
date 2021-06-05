package com.helloworld.domain.cart

import org.springframework.stereotype.Service
import java.util.*

@Service
class DomainCartQueryService(val cartRepository: CartRepository) {
    fun queryByAccountId(accountId: Long): Optional<Cart> {
        return cartRepository.findByAccountId(accountId)
    }
}