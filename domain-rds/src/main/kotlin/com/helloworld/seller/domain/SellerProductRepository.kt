package com.helloworld.seller.domain

import org.springframework.data.jpa.repository.JpaRepository

interface SellerProductRepository : JpaRepository<SellerProduct, Long>
