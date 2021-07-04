package com.helloworld.product.domain

import org.springframework.data.jpa.repository.JpaRepository

interface ProductOptionRepository : JpaRepository<ProductOption, Long> {
    fun findByProductId(productId: Long): List<ProductOption>
}
