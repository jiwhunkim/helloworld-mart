package com.helloworld.domain.product

import org.springframework.data.jpa.repository.JpaRepository

interface SellerProductRepository : JpaRepository<SellerProduct, Long> {
}