package com.helloworld.domain.product

import org.springframework.data.jpa.repository.JpaRepository

interface SkuRepository : JpaRepository<Sku, Long>
