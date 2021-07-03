package com.helloworld.sku.domain

import org.springframework.data.jpa.repository.JpaRepository

interface SkuRepository : JpaRepository<Sku, Long>
