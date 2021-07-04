package com.helloworld.display.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DisplayProductRepository : JpaRepository<DisplayProduct, Long> {
    fun findByProductId(productId: Long): Optional<DisplayProduct>
}
