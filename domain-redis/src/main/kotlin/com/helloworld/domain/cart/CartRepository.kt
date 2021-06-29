package com.helloworld.domain.cart

import org.springframework.data.repository.CrudRepository
import java.util.*

interface CartRepository : CrudRepository<Cart, String> {
    fun findByAccountId(accountId: Long): Optional<Cart>
}
